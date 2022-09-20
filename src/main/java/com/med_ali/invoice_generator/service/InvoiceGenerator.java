package com.med_ali.invoice_generator.service;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.models.CommandLine;
import org.springframework.stereotype.Service;
import com.lowagie.text.Table;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.Color.BLACK;

@Service
public class InvoiceGenerator {
    private final static Image logo;
    private final  static  Document document = new Document();
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private static float widthFile;
    static {
        try {
            logo = Image.getInstance("image.png");
            logo.setWidthPercentage(5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    public static final Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
    static {
        titleFont.setColor(BLACK);
    }

    private void addRowToTable(Table table, String[] data, boolean isHeader) {
        if(isHeader)
        {
            cellFont.setStyle(Font.BOLD);
        }
        for (String columnName : data) {
            Phrase phrase = new Phrase(columnName);
            phrase.setFont(cellFont);
            Cell cell = new Cell(phrase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    private static void createHeader(Command command)
    {
        Table table = new Table(2);
        table.setWidth(widthFile * 10 / 100);
        table.setWidths(new int[] {1, 2});
        table.setBorder(Table.NO_BORDER);
        logo.setAlignment(Element.ALIGN_LEFT);
        Cell imageCell = new Cell(logo);
        Phrase commandIdPhrase = new Phrase("Numéro de facture "+command.getId());
        commandIdPhrase.setFont(FontFactory.getFont("Roboto", 12));
        Phrase datePhrase = new Phrase("\n Date: "+new SimpleDateFormat("dd-MM-yy").format(new Date()));
        Paragraph paragraph = new Paragraph("");
        paragraph.add(commandIdPhrase);
        paragraph.add(datePhrase);
        Cell textCell = new Cell(paragraph);
        textCell.setBorder(Rectangle.NO_BORDER);
        textCell.setBorderColor(Color.WHITE);
        imageCell.setBorder(Rectangle.NO_BORDER);
        textCell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(imageCell);
        table.addCell(paragraph);
        Paragraph c = new Paragraph("Destination:\n");
        Phrase nameReceiver = new Phrase(command.getReceiver().getFirstname() + command.getReceiver().getLastname(), new Font(Font.BOLD));
        Phrase nameSender = new Phrase(command.getReceiver().getFirstname() + command.getReceiver().getLastname(), new Font(Font.BOLD));
        Phrase addressAndTvaCodeReceiver = new Phrase(command.getReceiver().getAddress() + "\nNuméro TVA"+command.getReceiver().getCodeTva());
        Phrase addressAndTvaCodeSender = new Phrase("Numéro de TVA: "+command.getSender().getCodeTva());
        Paragraph c1 = new Paragraph();
        c1.add(nameSender);
        c1.add(addressAndTvaCodeSender);
        Paragraph p1 = new Paragraph();
        c.add(nameReceiver);
        c.add(addressAndTvaCodeReceiver);
        c.setAlignment(Element.ALIGN_LEFT);
        p1.add(c);
        c1.setAlignment(Element.ALIGN_RIGHT);
        p1.add(c1);
        document.setMargins(0, 0, 0, 0);
        document.add(table);
        document.add(p1);
    }

    public OutputStream generatePdf(Command command) throws IOException, DocumentException {
        OutputStream fileOutputStream = Files.newOutputStream(Paths.get("model.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        document.newPage();
        document.open();
        document.add(new Chunk(""));
        writer.open();
        widthFile = document.getPageSize().getWidth();
        Table table = new Table(5);
        table.setBorder(Rectangle.NO_BORDER);
        String [] headers = {"nom de produit", "description", "sous-total(TND)", "TVA (TND)\n19%", "Total(TND)"};
        addRowToTable(table, headers, true);
        for (CommandLine commandLine: command.getCommandLines())
        {
            String description = "date start publication :\t" + dateFormat.format(commandLine.getDatePublicationStart()) + '\n'
                    +"date end publication :\t "+dateFormat.format(commandLine.getDatePublicationEnd());
            addRowToTable(table, new String[]{commandLine.getProductName(), description, String.valueOf(commandLine.getSubtotal()), String.valueOf(commandLine.getTva()), String.valueOf(commandLine.getTotal())}, false);
        }
        createHeader(command);
        table.setWidth(100);
        Paragraph paragraph = new Paragraph("Sous-total (TND):\t \t" + command.getSubtotal() +
                "\nTVA (TND):    \t \t"+command.getTva());
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        System.out.println(document.getPageSize().getWidth());
        document.add(table);
        document.add(paragraph);
        document.close();
        writer.close();
        return fileOutputStream;
    }
}
