package com.med_ali.invoice_generator.service;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import com.lowagie.text.Table;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

@Service
public class InvoiceGenerator {

    public static final Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    public static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 16);
    private static final String[] columnNames = {"hello", "hello", "hello", "hello"};
    private void addRowToTable(Table table, String[] data)
    {

        for (String columnName : data) {
            Cell cell = new Cell(new Phrase(columnName));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.BLACK);
            table.addCell(cell);
        }
    }
    public void generatePdf() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("model.pdf")));
        document.newPage();
        document.open();
        writer.open();
        Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, RED);
        Paragraph para = new Paragraph("Hello! This PDF is created using openPDF", font);
        para.setAlignment(Element.ALIGN_CENTER);
        Table table = new Table(5);
        String [] headers = {"description", "sous-total(TND)", "TVA (TND)\n19%", "Total(TND)"};
        addRowToTable();
        document.add(new Chunk(""));
        document.add(para);
        document.add(table);
        document.close();
        writer.close();
    }
}

