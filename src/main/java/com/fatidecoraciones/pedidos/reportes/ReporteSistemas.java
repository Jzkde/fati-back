package com.fatidecoraciones.pedidos.reportes;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.List;

public class ReporteSistemas {

    public static void repoSistemas (Document document, List<Presupuesto> presupuestos) { // addTableForOtherSystems
        Paragraph espacio = new Paragraph("").setFontSize(16);

        float[] columnWidths = {8, 8, 5, 5, 16, 16, 16, 24};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Encabezados de la tabla (centrados y en negrita)
        table.addHeaderCell(new Cell().add(new Paragraph("Ancho").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Alto").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Comando").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Apertura").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Tela").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Color").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Ambiente").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addHeaderCell(new Cell().add(new Paragraph("Sistema").setBold()).setTextAlignment(TextAlignment.CENTER).setFontSize(10));

        // Añadir filas con los datos de los presupuestos filtrados
        for (Presupuesto presupuesto : presupuestos) {
            table.addCell(String.valueOf(presupuesto.getAncho())).setFontSize(10);
            table.addCell(String.valueOf(presupuesto.getAlto())).setFontSize(10);
            table.addCell(presupuesto.getComando() == Comando.NO_POSEE ? "---" : presupuesto.getComando().toString()).setFontSize(10);
            table.addCell(presupuesto.getApertura() == Apertura.NO_POSEE ? "---" : presupuesto.getApertura().toString()).setFontSize(10);
            table.addCell("").setFontSize(10); // tela
            table.addCell("").setFontSize(10); // color
            table.addCell(presupuesto.getAmbiente()).setFontSize(10);
            table.addCell(presupuesto.getSistema().name()).setFontSize(10);
        }
        document.add(table);
        document.add(espacio);

        float[] columnWidths2 = {50, 50};

        Table table3 = new Table(UnitValue.createPercentArray(columnWidths2));
        table3.setWidth(UnitValue.createPercentValue(100));

        int contador = 1;
        for (Presupuesto presupuesto : presupuestos) {
            table3.addCell(new Cell().add(new Paragraph("Obs "+contador+ " : " + presupuesto.getObservaciones())).setFontSize(10));
            contador ++;
        }

        document.add(table3);
        document.add(espacio);

        Table table2 = new Table(UnitValue.createPercentArray(columnWidths2));
        table2.setWidth(UnitValue.createPercentValue(100));

        table2.addCell("Precio $ ").setFontSize(10);
        table2.addCell("Pedido Nº ").setFontSize(10);

        document.add(table2);
    }
}
