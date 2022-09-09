package com.med_ali.invoice_generator.service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.models.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
public class InvoiceGenerator {

    public static final Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    public static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 16);
    private static final String[] columnNames = {"hello", "hello", "hello", "hello"};
    public void addRowToTable(PdfPTable pdfPTable, String[] data)
    {

        for (String columnName : data) {
            PdfPCell cell = new PdfPCell(new Phrase(columnName));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLACK);
            pdfPTable.addCell(cell);
        }
    }
    public void generatePdf() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("model.pdf")));
        document.newPage();
        document.open();
        // get the table
        PdfPTable table = new PdfPTable(4);
        String [] headers = {"description", "sous-total(TND)", "TVA (TND)\n19%", "Total(TND)"};
        for (String columnName : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(columnName));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cell);
        }
        // addRowToTable(table, headers);
        document.add(new Chunk(""));
        table.setHeaderRows(1);
        document.add(table);
        document.add(table);
        document.close();
    }
}
