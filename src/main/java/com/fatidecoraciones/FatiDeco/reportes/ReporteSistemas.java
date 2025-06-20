package com.fatidecoraciones.FatiDeco.reportes;

import com.fatidecoraciones.FatiDeco.dtos.MedidaDto;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.List;

public class ReporteSistemas {

    public static void repoSistemas(Document document, List<MedidaDto> medidas, String sistema) { // addTableForOtherSystems
        Paragraph espacio = new Paragraph("").setFontSize(16);

        float[] columnWidths = {6, 6, 6, 6, 6, 18, 18, 18, 16};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Encabezados de la tabla (centrados y en negrita)
        table.addHeaderCell(new Cell().add(new Paragraph("Ancho").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Alto").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Com").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Ap").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));


        String encabezadoCaidaLargo = sistema.equalsIgnoreCase("ROLLER") ? "Caida" : "Largo";
        table.addHeaderCell(new Cell().add(new Paragraph(encabezadoCaidaLargo).setBold())
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10));

        table.addHeaderCell(new Cell().add(new Paragraph("Tela").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Color").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Ambiente").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Sistema").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));

        // Añadir filas con los datos de los medidas filtrados
        for (MedidaDto medida : medidas) {
            table.addCell(String.valueOf(medida.getAncho())).setFontSize(10);
            table.addCell(String.valueOf(medida.getAlto())).setFontSize(10);
            String textoComando = switch (medida.getComando()) {
                case NO_POSEE -> "---";
                case DERECHO -> "DER";
                case IZQUIERDO -> "IZQ";
                default -> medida.getComando().toString();
            };
            table.addCell(new Cell().add(new Paragraph(textoComando)).setFontSize(10));

            String textoApertura = switch (medida.getApertura()) {
                case NO_POSEE -> "---";
                case DERECHA -> "DER";
                case IZQUIERDA -> "IZQ";
                case CENTRAL -> "CNT";
                case BILATERAL -> "BIL";
                default -> medida.getComando().toString();
            };
            table.addCell(new Cell().add(new Paragraph(textoApertura)).setFontSize(10));

            table.addCell(new Cell().add(new Paragraph(medida.isCaida() ? "SI" : "NO")));
            table.addCell("").setFontSize(10); // tela
            table.addCell("").setFontSize(10); // color
            table.addCell(medida.getAmbiente()).setFontSize(10);
            table.addCell(medida.getSistema()).setFontSize(10);
        }
        document.add(table);
        document.add(espacio);

        float[] columnWidths3 = {100};

        Table table3 = new Table(UnitValue.createPercentArray(columnWidths3));
        table3.setWidth(UnitValue.createPercentValue(100));


        StringBuilder observaciones = new StringBuilder();
        int contador = 0;

        for (MedidaDto medida : medidas) {
            contador++;
            if (!medida.getObservaciones().isEmpty()) {

                observaciones.append("Obs ").append(contador).append(": ")
                        .append(medida.getObservaciones()).append(" - ");
            }
        }

        table3.addCell(new Cell()
                .add(new Paragraph(observaciones.toString()))
                .setFontSize(10));

        document.add(table3);

        document.add(espacio);

        float[] columnWidths2 = {50, 50};
        Table table2 = new Table(UnitValue.createPercentArray(columnWidths2));
        table2.setWidth(UnitValue.createPercentValue(100));

        table2.addCell("Precio $ ").setFontSize(10);
        table2.addCell("Pedido Nº ").setFontSize(10);

        document.add(table2);
    }
}