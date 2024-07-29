package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.criteria.PresupuestoCriteria;
import com.fatidecoraciones.pedidos.dtos.BusquedaDto;
import com.fatidecoraciones.pedidos.dtos.PresupuestoDto;
import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.fatidecoraciones.pedidos.reportes.ReporteSistemas;
import com.fatidecoraciones.pedidos.reportes.ReporteTelas;
import com.fatidecoraciones.pedidos.services.PresupuestoService;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("presupuesto")
@CrossOrigin
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @GetMapping("/lista")
    public ResponseEntity<List<PresupuestoDto>> lista() {
        List<PresupuestoDto> list = presupuestoService.getPresupuestoDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<PresupuestoDto> uno(@PathVariable("id") Long id) {

        if (!presupuestoService.existById(id)) {
            return new ResponseEntity("El CLIENTE no existe", HttpStatus.BAD_REQUEST);
        }

        PresupuestoDto uno = presupuestoService.getPresupuestoDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Presupuesto>> filtroUno(@PathVariable("id") Long id) {
        BusquedaDto busquedaDto = new BusquedaDto();
        PresupuestoCriteria presupuestoCriteria = createCriteria(busquedaDto);
        List<Presupuesto> list = presupuestoService.findByCriteria(presupuestoCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Presupuesto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        PresupuestoCriteria presupuestoCriteria = createCriteria(busquedaDto);
        List<Presupuesto> list = presupuestoService.findByCriteria(presupuestoCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody PresupuestoDto nuevo) {

        if (nuevo.getSistema() == null)
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        if (nuevo.getAlto() == 0)
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        if (nuevo.getAncho() == 0)
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        if (nuevo.getComando() == null)
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        if (nuevo.getApertura() == null)
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);
        if (
                (nuevo.getSistema() == Sistema.DUBAI ||
                        nuevo.getSistema() == Sistema.PERSIANA ||
                        nuevo.getSistema() == Sistema.ROLLER ||
                        nuevo.getSistema() == Sistema.ORIENTAL) && nuevo.getApertura() != Apertura.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene APERTURA", HttpStatus.BAD_REQUEST);
        if (
                nuevo.getSistema() == Sistema.TELA && nuevo.getComando() != Comando.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene COMANDO", HttpStatus.BAD_REQUEST);


        if (
                nuevo.getSistema() == Sistema.DUBAI ||
                        nuevo.getSistema() == Sistema.PERSIANA ||
                        nuevo.getSistema() == Sistema.ROLLER ||
                        nuevo.getSistema() == Sistema.ORIENTAL) {

            Presupuesto presupuesto = new Presupuesto(
                    nuevo.getSistema(),
                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    nuevo.getComando(),
                    Apertura.NO_POSEE,
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones(),
                    nuevo.getClienteNombre()
            );

            presupuestoService.save(presupuesto);
        }
        if (
                nuevo.getSistema() == Sistema.TELA) {

            Presupuesto presupuesto = new Presupuesto(
                    nuevo.getSistema(),
                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    Comando.NO_POSEE,
                    nuevo.getApertura(),
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones(),
                    nuevo.getClienteNombre()
            );

            presupuestoService.save(presupuesto);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody PresupuestoDto editar) {

        if (editar.getSistema() == null)
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        if (editar.getAlto() == 0)
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        if (editar.getAncho() == 0)
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        if (editar.getComando() == null)
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        if (editar.getApertura() == null)
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);

        if (
                (editar.getSistema() == Sistema.DUBAI ||
                        editar.getSistema() == Sistema.PERSIANA ||
                        editar.getSistema() == Sistema.ROLLER ||
                        editar.getSistema() == Sistema.ORIENTAL) && editar.getApertura() != Apertura.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene APERTURA", HttpStatus.BAD_REQUEST);
        if (
                editar.getSistema() == Sistema.TELA && editar.getComando() != Comando.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene COMANDO", HttpStatus.BAD_REQUEST);


        Presupuesto presupuesto = presupuestoService.getPresupuesto(id);

        presupuesto.setSistema(editar.getSistema());
        presupuesto.setAncho(editar.getAncho());
        presupuesto.setAlto(editar.getAlto());
        presupuesto.setComando(editar.getComando());
        presupuesto.setApertura(editar.getApertura());
        presupuesto.setAccesorios(editar.getAccesorios());
        presupuesto.setAmbiente(editar.getAmbiente());
        presupuesto.setObservaciones(editar.getObservaciones());
        presupuesto.setClienteNombre(editar.getClienteNombre());


        presupuestoService.save(presupuesto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!presupuestoService.existById(id))
            return new ResponseEntity<>("El PRESUPUESTO no existe", HttpStatus.NOT_FOUND);
        presupuestoService.delete(id);
        return new ResponseEntity<>("PRESUPUESTO borrado", HttpStatus.OK);
    }

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> generarPdf(@RequestBody List<Presupuesto> presupuestos) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Map<Sistema, List<Presupuesto>> presupuestosPorSistema = presupuestos.stream()
                    .collect(Collectors.groupingBy(Presupuesto::getSistema));

            Set<Sistema> sistemas = presupuestosPorSistema.keySet();

            if (sistemas.size() == 1) {
                // Si solo hay un tipo de sistema, generar un solo archivo PDF
                Sistema unicoSistema = sistemas.iterator().next();
                ByteArrayOutputStream pdfBaos = new ByteArrayOutputStream();

                PdfWriter writer = new PdfWriter(pdfBaos);
                PdfDocument pdfDocument = new PdfDocument(writer);

                // Configurar tamaño y orientación
                PageSize pageSize;
                if (unicoSistema == Sistema.TELA) {
                    pageSize = new PageSize(PageSize.A4.getHeight() / 2, PageSize.A4.getWidth());
                } else {
                    pageSize = new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                }

                pdfDocument.setDefaultPageSize(pageSize);
                Document document = new Document(pdfDocument, pageSize);

                Paragraph titulo = new Paragraph("FATI Decoraciones").setUnderline().setItalic().setTextAlignment(TextAlignment.CENTER).setFontSize(16);
                document.add(titulo);
                Paragraph espacio = new Paragraph("").setFontSize(16);
                document.add(espacio);
                Paragraph cliente = new Paragraph("Cliente:____________________  Tel:____________________").setFontSize(12);
                document.add(cliente);
                Paragraph domicilio = new Paragraph("Domicilio:__________________________________________").setFontSize(12);
                document.add(domicilio);
                document.add(espacio);
                Paragraph sist = new Paragraph("Sistema: " + unicoSistema).setUnderline().setFontSize(12);
                document.add(sist);

                if (unicoSistema == Sistema.TELA) {
                    ReporteTelas.repoTelas(document, presupuestosPorSistema.get(Sistema.TELA));
                } else {
                    ReporteSistemas.repoSistemas(document, presupuestosPorSistema.get(unicoSistema));
                }

                document.close();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "presupuesto_" + unicoSistema.name().toLowerCase() + ".pdf");
                headers.setContentLength(pdfBaos.size());

                return new ResponseEntity<>(pdfBaos.toByteArray(), headers, HttpStatus.OK);

            } else {
                // Si hay más de un tipo de sistema, generar un archivo ZIP
                try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
                    for (Map.Entry<Sistema, List<Presupuesto>> entry : presupuestosPorSistema.entrySet()) {
                        Sistema sistema = entry.getKey();
                        List<Presupuesto> presupuestosDelSistema = entry.getValue();

                        ByteArrayOutputStream pdfBaos = new ByteArrayOutputStream();
                        PdfWriter writer = new PdfWriter(pdfBaos);
                        PdfDocument pdfDocument = new PdfDocument(writer);

                        // Configurar tamaño y orientación
                        PageSize pageSize;
                        if (sistema == Sistema.TELA) {
                            pageSize = new PageSize(PageSize.A4.getHeight() / 2, PageSize.A4.getWidth());
                        } else {
                            pageSize = new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        }

                        pdfDocument.setDefaultPageSize(pageSize);
                        Document document = new Document(pdfDocument, pageSize);

                        Paragraph titulo = new Paragraph("FATI Decoraciones").setUnderline().setItalic().setTextAlignment(TextAlignment.CENTER).setFontSize(16);
                        document.add(titulo);
                        Paragraph espacio = new Paragraph("").setFontSize(16);
                        document.add(espacio);
                        Paragraph cliente = new Paragraph("Cliente:____________________  Tel:____________________").setFontSize(12);
                        document.add(cliente);
                        Paragraph domicilio = new Paragraph("Domicilio:__________________________________________").setFontSize(12);
                        document.add(domicilio);
                        document.add(espacio);
                        Paragraph sist = new Paragraph("Sistema: " + sistema).setUnderline().setFontSize(12);
                        document.add(sist);

                        if (sistema == Sistema.TELA) {
                            ReporteTelas.repoTelas(document, presupuestosDelSistema);
                        } else {
                            ReporteSistemas.repoSistemas(document, presupuestosDelSistema);
                        }

                        document.close();

                        String zipEntryName = "presupuesto_" + sistema.name().toLowerCase() + ".pdf";
                        ZipEntry zipEntry = new ZipEntry(zipEntryName);
                        zipOut.putNextEntry(zipEntry);
                        zipOut.write(pdfBaos.toByteArray());
                        zipOut.closeEntry();
                    }
                }

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "presupuestos.zip");
                headers.setContentLength(baos.size());

                return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private PresupuestoCriteria createCriteria(BusquedaDto busqueda) {
        PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria();
        if (busqueda != null) {

            //Cliente
            if (!StringUtils.isBlank(busqueda.getClienteNombre())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getClienteNombre());
                presupuestoCriteria.setClienteNombre(filter);
            }
        }
        return presupuestoCriteria;
    }

}
