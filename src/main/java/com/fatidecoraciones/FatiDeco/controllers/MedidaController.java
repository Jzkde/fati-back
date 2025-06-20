package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.MedidaCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.MedidaDto;
import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.enums.Comando;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.reportes.ReporteSistemas;
import com.fatidecoraciones.FatiDeco.reportes.ReporteTelas;
import com.fatidecoraciones.FatiDeco.services.ClienteService;
import com.fatidecoraciones.FatiDeco.services.MedidaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("medidas")
@CrossOrigin
public class MedidaController {

    private final MedidaService medidaService;
    private final ClienteService clienteService;
    private final SistemaService sistemaService;

    public MedidaController(MedidaService medidaService,
                            ClienteService clienteService,
                            SistemaService sistemaService) {
        this.medidaService = medidaService;
        this.clienteService = clienteService;
        this.sistemaService = sistemaService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<MedidaDto>> lista() {
        List<MedidaDto> list = medidaService.getMedidaDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<MedidaDto> uno(@PathVariable Long id) {

        if (!medidaService.existById(id)) {
            return new ResponseEntity("Las MEDIDAS no existen o fueron BORRADAS", HttpStatus.BAD_REQUEST);
        }
        MedidaDto uno = medidaService.getMedidaDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/filtro")
    public ResponseEntity<List<MedidaDto>> filtro(@RequestBody BusquedaDto busquedaDto) {

        medidaService.actualizarMedidasViejas();
        MedidaCriteria medidaCriteria = createCriteria(busquedaDto);
        List<Medida> lista = medidaService.findByCriteria(medidaCriteria);

        List<MedidaDto> listaDto = lista.stream().map(medida -> {
            MedidaDto medidaDto = new MedidaDto();
            medidaDto.setId(medida.getId());
            medidaDto.setAncho(medida.getAncho());
            medidaDto.setAlto(medida.getAlto());
            medidaDto.setComando(medida.getComando());
            medidaDto.setApertura(medida.getApertura());
            medidaDto.setAccesorios(medida.getAccesorios());
            medidaDto.setAmbiente(medida.getAmbiente());
            medidaDto.setObservaciones(medida.getObservaciones());
            medidaDto.setFecha(medida.getFecha());
            medidaDto.setViejo(medida.isViejo());
            medidaDto.setComprado(medida.isComprado());
            medidaDto.setCaida(medida.isCaida());
            medidaDto.setCliente(medida.getCliente() != null ? medida.getCliente().getNombre() : null);
            medidaDto.setSistema(medida.getSistema() != null ? medida.getSistema().getSistema() : null);
            return medidaDto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody MedidaDto nuevo) {
        Cliente cliente;

        if (nuevo.getCliente() == null) {
            return new ResponseEntity<>("Falta el NOMBRE del Cliente", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getSistema() == null) {
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getAlto() == 0) {
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getAncho() == 0) {
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getComando() == null) {
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getApertura() == null) {
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);
        }

        Sistema sistema = sistemaService.findBySistema(nuevo.getSistema());
        System.out.println(sistema.getSistema());
        if (clienteService.existByNombre(nuevo.getCliente().trim())) {
            cliente = clienteService.findByNombre(nuevo.getCliente().trim());
        } else {
            cliente = new Cliente();
            cliente.setNombre(nuevo.getCliente().trim());
            cliente = clienteService.save(cliente);
        }

        Set<String> sistemasValidos = Set.of("DUBAI", "PERSIANA", "ROLLER", "ORIENTAL", "ROMANA", "ZEBRA");

        if (sistemasValidos.contains(nuevo.getSistema().toUpperCase())) {

            if (nuevo.getComando() == Comando.NO_POSEE || nuevo.getApertura() != Apertura.NO_POSEE) {
                return new ResponseEntity<>("Este SISTEMA requiere COMANDO y NO debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if (nuevo.getSistema() == "VERTICALES") {

            if (nuevo.getComando() == Comando.NO_POSEE || nuevo.getApertura() == Apertura.NO_POSEE) {
                return new ResponseEntity<>("VERTICALES requiere COMANDO y APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if (nuevo.getSistema() == "TELA") {

            if (nuevo.getComando() != Comando.NO_POSEE || nuevo.getApertura() == Apertura.NO_POSEE) {
                return new ResponseEntity<>("TELA no debe tener COMANDO y debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("SISTEMA inválido", HttpStatus.BAD_REQUEST);
        }

        Medida medida;

        if (sistemasValidos.contains(nuevo.getSistema().toUpperCase())) {

            medida = new Medida(

                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    nuevo.getComando(),
                    Apertura.NO_POSEE,
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones(),
                    nuevo.getCliente().trim(),
                    LocalDate.now(),
                    false,
                    false,
                    nuevo.isCaida()
            );
        } else if (nuevo.getSistema().equalsIgnoreCase("VERTICALES")) {

            medida = new Medida(

                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    nuevo.getComando(),
                    nuevo.getApertura(),
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones(),
                    nuevo.getCliente().trim(),
                    LocalDate.now(),
                    false,
                    false,
                    nuevo.isCaida()
            );

        } else if (nuevo.getSistema().equalsIgnoreCase("TELA")) {

            medida = new Medida(

                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    Comando.NO_POSEE,
                    nuevo.getApertura(),
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones(),
                    nuevo.getCliente().trim(),
                    LocalDate.now(),
                    false,
                    false,
                    false
            );

        } else {
            return new ResponseEntity<>("SISTEMA inválido", HttpStatus.BAD_REQUEST);
        }

        cliente.addMedida(medida);
        sistema.addMedidas(medida);

        medidaService.save(medida);

        return new ResponseEntity<>("Medidas cargardas: " + medida.getId(), HttpStatus.OK);
    }


    @PostMapping("/pdf")
    public ResponseEntity<?> generarPdf(@RequestBody List<MedidaDto> medidas,
                                        @RequestParam String tel,
                                        @RequestParam String direcc) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Map<String, List<MedidaDto>> presupuestosPorSistema = medidas.stream()
                    .peek(m -> m.setSistema(m.getSistema().toUpperCase()))
                    .collect(Collectors.groupingBy(MedidaDto::getSistema));

            Set<String> sistemas = presupuestosPorSistema.keySet();

            // Si hay un solo tipo de Sistema genera solo un PDF
            if (sistemas.size() == 1) {
                String sistema = sistemas.iterator().next();
                Optional<byte[]> pdfBytesOpt = pdfSegunSistema(sistema.toUpperCase(), presupuestosPorSistema.get(sistema), tel, direcc);

                if (pdfBytesOpt.isPresent()) {
                    byte[] pdfBytes = pdfBytesOpt.get();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_PDF);
                    headers.setContentDispositionFormData("attachment", "presupuesto_" + sistema.toLowerCase() + ".pdf");
                    headers.setContentLength(pdfBytes.length);

                    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Los clientes no coinciden.", HttpStatus.BAD_REQUEST);
                }
            } else {
                // Si hay dos o más tipos de Sistema genera un ZIP con los PDFs correspondientes
                try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
                    for (Map.Entry<String, List<MedidaDto>> entry : presupuestosPorSistema.entrySet()) {

                        String sistema = entry.getKey();
                        Optional<byte[]> pdfBytesOpt = pdfSegunSistema(sistema.toUpperCase(), entry.getValue(), tel, direcc);

                        if (pdfBytesOpt.isPresent()) {
                            byte[] pdfBytes = pdfBytesOpt.get();
                            ZipEntry zipEntry = new ZipEntry("presupuesto_" + sistema.toLowerCase() + ".pdf");
                            zipOut.putNextEntry(zipEntry);
                            zipOut.write(pdfBytes);
                            zipOut.closeEntry();
                        } else {
                            return new ResponseEntity<>("Los clientes no coinciden.", HttpStatus.BAD_REQUEST);
                        }
                    }
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "presupuestos.zip");
                headers.setContentLength(baos.size());
                System.out.println("ZIP");
                return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/sync")
    @Transactional
    public ResponseEntity<?> sincronizarMedidas(@RequestBody List<MedidaDto> medidasDto) {
        try {
            List<Medida> medidas = new ArrayList<>();

            for (MedidaDto dto : medidasDto) {
                Sistema sistema = sistemaService.findBySistema(dto.getSistema());

                // Buscar o crear cliente
                Cliente cliente;
                if (clienteService.existByNombre(dto.getCliente().trim())) {
                    cliente = clienteService.findByNombre(dto.getCliente().trim());
                } else {
                    cliente = new Cliente();
                    cliente.setNombre(dto.getCliente().trim());
                    cliente = clienteService.save(cliente);
                }

                // Crear el medida asociado al cliente
                Medida nuevo = new Medida(
                        dto.getAncho(),
                        dto.getAlto(),
                        dto.getComando(),
                        dto.getApertura(),
                        dto.getAccesorios(),
                        dto.getAmbiente(),
                        dto.getObservaciones(),
                        cliente.getNombre(),
                        LocalDate.now(),
                        false,
                        false,
                        dto.isCaida()
                );
                cliente.addMedida(nuevo);
                sistema.addMedidas(nuevo);

                medidas.add(nuevo);
            }

            // Guardar todos los medidas
            medidaService.saveAll(medidas);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody MedidaDto editar) {
        Sistema sistema = sistemaService.findBySistema(editar.getSistema());

        if (editar.getSistema() == null) {
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        }
        if (editar.getAlto() == 0) {
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        }
        if (editar.getAncho() == 0) {
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        }
        if (editar.getComando() == null) {
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        }
        if (editar.getApertura() == null) {
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);
        }

        if ("ROLLER".equals(editar.getSistema()) ||
                "PERSIANA".equals(editar.getSistema()) ||
                "DUBAI".equals(editar.getSistema()) ||
                "ORIENTAL".equals(editar.getSistema()) ||
                "ROMANA".equals(editar.getSistema()) ||
                "ZEBRA".equals(editar.getSistema())) {

            if (editar.getComando() == Comando.NO_POSEE || editar.getApertura() != Apertura.NO_POSEE) {
                return new ResponseEntity<>("Este SISTEMA requiere COMANDO y NO debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if ("VERTICALES".equals(editar.getSistema())) {

            if (editar.getComando() == Comando.NO_POSEE || editar.getApertura() == Apertura.NO_POSEE) {
                return new ResponseEntity<>("VERTICALES requiere COMANDO y APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if ("TELA".equals(editar.getSistema())) {

            if (editar.getComando() != Comando.NO_POSEE || editar.getApertura() == Apertura.NO_POSEE) {
                return new ResponseEntity<>("TELA no debe tener COMANDO y debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("SISTEMA inválido", HttpStatus.BAD_REQUEST);
        }

        Medida medida = medidaService.getMedida(id);

        if (medida == null) {
            return new ResponseEntity<>("El medida no existe", HttpStatus.NOT_FOUND);
        }

        if (medida.getCliente() == null ||
                !medida.getCliente().getNombre().equals(editar.getCliente())) {

            Cliente cliente;

            if (clienteService.existByNombre(editar.getCliente())) {
                cliente = clienteService.findByNombre(editar.getCliente());
            } else {
                cliente = new Cliente();
                cliente.setNombre(editar.getCliente());
                cliente = clienteService.save(cliente);
            }

            cliente.addMedida(medida);
        }

        medida.setClienteNombre(editar.getCliente());
        medida.setAncho(editar.getAncho());
        medida.setAlto(editar.getAlto());
        medida.setComando(editar.getComando());
        medida.setApertura(editar.getApertura());
        medida.setAccesorios(editar.getAccesorios());
        medida.setAmbiente(editar.getAmbiente());
        medida.setObservaciones(editar.getObservaciones());
        medida.setFecha(editar.getFecha());
        medida.setViejo(editar.isViejo());
        medida.setComprado(editar.isComprado());
        medida.setCaida(editar.isCaida());

        medida.setSistema(sistema);

        medidaService.save(medida);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<?> actualizar(@PathVariable Long id) {
        if (!medidaService.existById(id)) {
            return new ResponseEntity<>("No Existe", HttpStatus.NOT_FOUND);
        }

        Medida medida = medidaService.getMedida(id);
        if (medida == null) {
            return new ResponseEntity<>("Medida no encontrado", HttpStatus.NOT_FOUND);
        }

        if (medida.isComprado()) {
            medida.setComprado(false);
        } else {
            medida.setComprado(true);
        }

        medidaService.save(medida);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!medidaService.existById(id))
            return new ResponseEntity<>("El PRESUPUESTO no existe", HttpStatus.NOT_FOUND);
        medidaService.delete(id);
        return new ResponseEntity<>("PRESUPUESTO borrado", HttpStatus.OK);
    }

    private Optional<byte[]> pdfSegunSistema(String sistema, List<MedidaDto> medidas, String tel, String direcc) throws IOException {
        // Crea el documento
        ByteArrayOutputStream pdfBaos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(pdfBaos);
        PdfDocument pdfDocument = new PdfDocument(writer);

        PageSize pageSize;
        if (sistema.equalsIgnoreCase("TELA")) {
            pageSize = new PageSize(PageSize.A4.getHeight() / 2, PageSize.A4.getWidth());
        } else {
            pageSize = new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        }

        pdfDocument.setDefaultPageSize(pageSize);
        Document document = new Document(pdfDocument, pageSize);

        if (agregarContenido(document, sistema, medidas, tel, direcc)) {
            document.close();
            return Optional.of(pdfBaos.toByteArray());
        } else {
            document.close();
            return Optional.empty();
        }
    }

    private boolean agregarContenido(Document document, String sistema, List<MedidaDto> medidas, String tel, String direcc) {

        Cliente cliente1 = clienteService.findByNombre(medidas.get(0).getCliente());

        if (cliente1 == null) {
            Cliente clienteN = new Cliente(
                    medidas.get(0).getCliente(),
                    direcc,
                    tel
            );
            clienteService.save(clienteN);
        } else {
            cliente1.setDireccion(direcc);
            cliente1.setTelefono(tel);

            clienteService.save(cliente1);
        }
        boolean cliente = medidaService.compararCliente(medidas);
        if (!cliente) {
            return false;
        }

        // Encabezado
        Paragraph titulo = new Paragraph("FATI Decoraciones")
                .setUnderline()
                .setItalic()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16);
        document.add(titulo);

        Paragraph espacio = new Paragraph("").setFontSize(16);
//        document.add(espacio);

        float[] columnWidths = {45, 10, 45};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell(new Cell().add(new Paragraph("Cliente: " + medidas.get(0).getCliente()).setUnderline()).setFontSize(10).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Telefono: " + tel).setUnderline()).setFontSize(10).setBorder(Border.NO_BORDER));

        Cell direccion = new Cell(1, 3).add(new Paragraph("Domicilio: " + direcc)
                .setFontSize(10)
                .setBorder(Border.NO_BORDER));
        table.addCell(direccion.setUnderline().setBorder(Border.NO_BORDER));

        document.add(table);

        Paragraph sist = new Paragraph("Sistema: " + sistema).setUnderline().setFontSize(12);
        document.add(sist);

        // Tablas según Sistema
        if (sistema.equalsIgnoreCase("TELA")) {
            ReporteTelas.repoTelas(document, medidas);
        } else {
            ReporteSistemas.repoSistemas(document, medidas, sistema);
        }

        return true;
    }

    private MedidaCriteria createCriteria(BusquedaDto busqueda) {
        MedidaCriteria medidaCriteria = new MedidaCriteria();
        if (busqueda != null) {

            //Cliente
            if (!StringUtils.isBlank(busqueda.getCliente())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getCliente());
                medidaCriteria.setClienteNombre(filter);
            }
            //Viejo
            if (!StringUtils.isBlank(busqueda.getViejo())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getViejo().equals("true")) {
                    filter.setEquals(true);
                } else {
                    filter.setEquals(false);
                }
                medidaCriteria.setViejo(filter);
            }
            //Llego
            if (!StringUtils.isBlank(busqueda.getComprado())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getComprado().equals("true")) {
                    filter.setEquals(true);
                } else {
                    filter.setEquals(false);
                }
                medidaCriteria.setComprado(filter);
            }
        }
        return medidaCriteria;
    }
}