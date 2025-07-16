package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.MedidaCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.MedidaDto;
import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.enums.Comando;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.ClienteService;
import com.fatidecoraciones.FatiDeco.services.MedidaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.io.InputStream;
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
            medidaDto.setComando(medida.getComando().toString());
            medidaDto.setApertura(medida.getApertura().toString());
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

        if (clienteService.existByNombre(nuevo.getCliente().trim())) {
            cliente = clienteService.findByNombre(nuevo.getCliente().trim());
        } else {
            cliente = new Cliente();
            cliente.setNombre(nuevo.getCliente().trim());
            cliente = clienteService.save(cliente);
        }

        Set<String> sistemasValidos = Set.of("DUBAI", "PERSIANA", "ROLLER", "ORIENTAL", "ROMANA", "ZEBRA");

        if (sistemasValidos.contains(nuevo.getSistema().toUpperCase())) {

            if (nuevo.getComando().equalsIgnoreCase("NO_POSEE") || !nuevo.getApertura().equalsIgnoreCase("NO_POSEE")) {
                return new ResponseEntity<>("Este SISTEMA requiere COMANDO y NO debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if (nuevo.getSistema().equalsIgnoreCase("VERTICALES")) {

            if (nuevo.getComando().equalsIgnoreCase("NO_POSEE") || nuevo.getApertura().equalsIgnoreCase("NO_POSEE")) {
                return new ResponseEntity<>("VERTICALES requiere COMANDO y APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if (nuevo.getSistema().equalsIgnoreCase("TELA")) {

            if (!nuevo.getComando().equalsIgnoreCase("NO_POSEE") || nuevo.getApertura().equalsIgnoreCase("NO_POSEE")) {
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
                    Comando.valueOf(nuevo.getComando()),
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
                    Comando.valueOf(nuevo.getComando()),
                    Apertura.valueOf(nuevo.getComando()),
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
                    Apertura.valueOf(nuevo.getComando()),
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
    public ResponseEntity<?> generarPdfJasper(@RequestBody List<MedidaDto> medidas,
                                              @RequestParam String tel,
                                              @RequestParam String direcc) {

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

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Verificar que todos los clientes sean iguales
            String clienteReferencia = medidas.get(0).getCliente();
            boolean clientesIguales = medidas.stream().allMatch(m -> m.getCliente().equalsIgnoreCase(clienteReferencia));
            if (!clientesIguales) {
                return new ResponseEntity<>("Los clientes no coinciden.", HttpStatus.BAD_REQUEST);
            }


            Map<String, List<MedidaDto>> medidasPorSistema = medidas.stream()
                    .peek(m -> m.setSistema(m.getSistema().toUpperCase()))
                    .collect(Collectors.groupingBy(MedidaDto::getSistema));

            if (medidasPorSistema.size() == 1) {
                String sistema = medidasPorSistema.keySet().iterator().next();
                byte[] pdf = generarPdfConJasper(sistema, medidasPorSistema.get(sistema), tel, direcc);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "presupuesto_" + sistema.toLowerCase() + ".pdf");
                headers.setContentLength(pdf.length);

                return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
            } else {
                try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
                    for (Map.Entry<String, List<MedidaDto>> entry : medidasPorSistema.entrySet()) {
                        String sistema = entry.getKey();
                        byte[] pdf = generarPdfConJasper(sistema, entry.getValue(), tel, direcc);

                        ZipEntry zipEntry = new ZipEntry("presupuesto_" + sistema.toLowerCase() + ".pdf");
                        zipOut.putNextEntry(zipEntry);
                        zipOut.write(pdf);
                        zipOut.closeEntry();
                    }
                }

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "presupuestos.zip");
                headers.setContentLength(baos.size());

                return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al generar el PDF", HttpStatus.INTERNAL_SERVER_ERROR);
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
                        Comando.valueOf(dto.getComando()),
                        Apertura.valueOf(dto.getApertura()),
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

            if (editar.getComando().equalsIgnoreCase("NO_POSEE") || !editar.getApertura().equalsIgnoreCase("NO_POSEE")) {
                return new ResponseEntity<>("Este SISTEMA requiere COMANDO y NO debe tener APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if ("VERTICALES".equals(editar.getSistema())) {

            if (editar.getComando().equalsIgnoreCase("NO_POSEE") || editar.getApertura().equalsIgnoreCase("NO_POSEE")) {
                return new ResponseEntity<>("VERTICALES requiere COMANDO y APERTURA", HttpStatus.BAD_REQUEST);
            }

        } else if ("TELA".equals(editar.getSistema())) {

            if (!editar.getComando().equalsIgnoreCase("NO_POSEE") || editar.getApertura().equalsIgnoreCase("NO_POSEE")) {
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
        medida.setComando(Comando.valueOf(editar.getComando()));
        medida.setApertura(Apertura.valueOf(editar.getApertura()));
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
            //Comprado
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

    public byte[] generarPdfConJasper(String sistema, List<MedidaDto> medidas, String telefono, String direccion) throws JRException, IOException {
        // Seleccionar el reporte según el sistema
        String jasperPath = sistema.equalsIgnoreCase("TELA")
                ? "/presupuesto_tela.jasper"
                : "/presupuesto_sistema.jasper";

        InputStream jasperStream = getClass().getResourceAsStream(jasperPath);

        Map<String, Object> params = new HashMap<>();
        params.put("CLIENTE", medidas.get(0).getCliente());
        params.put("DIRECCION", direccion);
        params.put("TELEFONO", telefono);
        params.put("SISTEMA", sistema);

        StringBuilder obsBuilder = new StringBuilder();
        int index = 1;
        for (MedidaDto m : medidas) {
            if (m.getObservaciones() != null && !m.getObservaciones().isEmpty()) {
                obsBuilder.append("Obs ").append(index).append(": ").append(m.getObservaciones()).append(" - ");
            }
            index++;
        }
        params.put("OBSERVACIONES", obsBuilder.toString());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(medidas);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}