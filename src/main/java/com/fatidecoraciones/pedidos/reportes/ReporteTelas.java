package com.fatidecoraciones.pedidos.reportes;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.util.List;

public class ReporteTelas {

    public static void repoTelas(Document document, List<Presupuesto> presupuestos) { //addTableForTela
        Paragraph espacio = new Paragraph("").setFontSize(16);

        for (Presupuesto presupuesto : presupuestos) {
            float[] columnWidths = {10, 15, 15, 10, 50};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Primera fila
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            Cell cellAncho = new Cell(1, 2).add(new Paragraph(String.valueOf(presupuesto.getAncho())));
            cellAncho.setFontSize(10);
            cellAncho.setTextAlignment(TextAlignment.CENTER);
            cellAncho.setBorder(Border.NO_BORDER);
            table.addCell(cellAncho);
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER));

            // Segunda fila
            Cell cellAlto = new Cell(4, 1).add(new Paragraph(String.valueOf(presupuesto.getAlto())));
            cellAlto.setFontSize(10);
            cellAlto.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cellAlto.setTextAlignment(TextAlignment.RIGHT);
            cellAlto.setBorder(Border.NO_BORDER);
            table.addCell(cellAlto);
            table.addCell(new Cell(4, 1).add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(1)).setBorderLeft(new SolidBorder(1)));


            if (presupuesto.getApertura() != Apertura.BILATERAL) {
                table.addCell(new Cell(4, 1).add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(1)));
            } else {
                table.addCell(new Cell(4, 1).add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(1)).setBorderLeft(new SolidBorder(1)));

            }

            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph("Tela: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            // Tercera fila
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph("Tela: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            // Cuarta fila
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph("Sistema: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            // Quinta fila
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph("Accesorios: " + presupuesto.getAccesorios())).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            document.add(table);
            document.add(espacio);

            float[] columnWidth = {10, 10, 80};
            Table table2 = new Table(UnitValue.createPercentArray(columnWidth));
            table2.setWidth(UnitValue.createPercentValue(100));

            table2.addCell(new Cell().add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell().add(new Paragraph("Obs: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell(1, 4).add(new Paragraph(presupuesto.getObservaciones())).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            document.add(table2);
            document.add(espacio);
        }
    }
}
