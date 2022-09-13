package com.med_ali.invoice_generator;

import com.lowagie.text.DocumentException;
import com.med_ali.invoice_generator.models.Client;
import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.models.CommandLine;
import com.med_ali.invoice_generator.service.InvoiceGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class InvoiceGeneratorApplicationTests {

    private final InvoiceGenerator invoiceGenerator;

    InvoiceGeneratorApplicationTests() throws DocumentException, IOException {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void generateInvoice() throws DocumentException, IOException {
        Client client = new Client("omar", "triki", "021544964", "sfax afran 3093");
        CommandLine commandLine0 = new CommandLine("THIS IS THE COMMAND LINE 1", (float) 125.14, 23.00F, 147F, 120.01F);
        CommandLine commandLine1 = new CommandLine("THIS IS THE COMMAND LINE 1", (float) 125.14, 23.00F, 147F, 120.01F);
        CommandLine commandLine2 = new CommandLine("THIS IS THE COMMAND LINE 1", (float) 125.14, 23.00F, 147F, 120.01F);
        CommandLine commandLine3 = new CommandLine("THIS IS THE COMMAND LINE 1", (float) 125.14, 23.00F, 147F, 120.01F);
        List<CommandLine> commandLines = new ArrayList<>();
        commandLines.add(commandLine0);
        commandLines.add(commandLine1);
        commandLines.add(commandLine2);
        commandLines.add(commandLine3);
        Command command = new Command(5463543546534L, client, new Date(), 155646, 12, 565464, commandLines);

        this.invoiceGenerator.generatePdf();
    }

}
