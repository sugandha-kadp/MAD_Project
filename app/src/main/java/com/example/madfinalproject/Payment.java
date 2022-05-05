package com.example.madfinalproject;

public class Payment {

    private String crdNumber;
    private String validUntil;
    private String cvv;
    private String crdHolder;

    public Payment(String crdNumber, String validUntil, String cvv, String crdHolder) {
        this.crdNumber = crdNumber;
        this.validUntil = validUntil;
        this.cvv = cvv;
        this.crdHolder = crdHolder;
    }

    public String getCrdNumber() {
        return crdNumber;
    }

    public void setCrdNumber(String crdNumber) {
        this.crdNumber = crdNumber;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCrdHolder() {
        return crdHolder;
    }

    public void setCrdHolder(String crdHolder) {
        this.crdHolder = crdHolder;
    }
}
