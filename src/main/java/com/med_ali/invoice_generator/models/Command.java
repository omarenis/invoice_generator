package com.med_ali.invoice_generator.models;


import java.util.Date;
import java.util.List;

public class Command {
    private Long id;
    private Client client;
    private Date date;
    private float subtotal;
    private float tva;
    private float total;
    private List<CommandLine> commandLines;
    public Command(Long id, Client client, Date date, float subtotal, float tva, float total, List<CommandLine> commandLineList) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.subtotal = subtotal;
        this.tva = tva;
        this.total = total;
        commandLines = commandLineList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }
}
