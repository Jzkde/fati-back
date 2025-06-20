package com.fatidecoraciones.FatiDeco.reportes;

import com.fatidecoraciones.FatiDeco.dtos.MedidaDto;
import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.util.List;

public class ReporteTelas {

    public static void repoTelas(Document document, List<MedidaDto> medidas) { //addTableForTela
        Paragraph espacio = new Paragraph("").setFontSize(16);

        for (MedidaDto medida : medidas) {
            float[] columnWidths = {10, 15, 15, 10, 50};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(80));

            // Primera fila
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            Cell cellAncho = new Cell(1, 2).add(new Paragraph(String.valueOf(medida.getAncho())));
            cellAncho.setFontSize(10);
            cellAncho.setTextAlignment(TextAlignment.CENTER);
            cellAncho.setBorder(Border.NO_BORDER);
            table.addCell(cellAncho);
            table.addCell(new Cell().add(new Paragraph("")).setFontSize(10).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER));

            // Segunda fila
            Cell cellAlto = new Cell(4, 1).add(new Paragraph(String.valueOf(medida.getAlto())));
            cellAlto.setFontSize(10);
            cellAlto.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cellAlto.setTextAlignment(TextAlignment.RIGHT);
            cellAlto.setBorder(Border.NO_BORDER);
            table.addCell(cellAlto);
            table.addCell(new Cell(4, 1).add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(1)).setBorderLeft(new SolidBorder(1)));


            if (medida.getApertura() != Apertura.BILATERAL) {
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
            table.addCell(new Cell().add(new Paragraph("Accesorios: " + medida.getAccesorios())).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));

            document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
            document.add(espacio);

            float[] columnWidth = {10, 10, 80};
            Table table2 = new Table(UnitValue.createPercentArray(columnWidth));
            table2.setWidth(UnitValue.createPercentValue(80));

            table2.addCell(new Cell().add(new Paragraph()).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell().add(new Paragraph("Amb: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell(1, 4).add(new Paragraph(medida.getAmbiente())).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell().add(new Paragraph("Obs: ")).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));
            table2.addCell(new Cell(1, 4).add(new Paragraph(medida.getObservaciones())).setFontSize(10).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1)));


            document.add(table2.setHorizontalAlignment(HorizontalAlignment.CENTER));
            document.add(espacio);
        }
    }
}