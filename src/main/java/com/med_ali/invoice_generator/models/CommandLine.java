package com.med_ali.invoice_generator.models;

public class CommandLine {
    private String description;
    private float subtotal;
    private float tva;
    private float total;
    private float benefitPub;

    public CommandLine(String description, float subtotal, float tva, float total, float benefitPub) {
        this.description = description;
        this.subtotal = subtotal;
        this.tva = tva;
        this.total = total;
        this.benefitPub = benefitPub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getBenefitPub() {
        return benefitPub;
    }

    public void setBenefitPub(float benefitPub) {
        this.benefitPub = benefitPub;
    }
}
