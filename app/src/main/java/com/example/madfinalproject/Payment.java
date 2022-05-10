package com.example.madfinalproject;

public class Payment {
    //Variables
    private String crdNumber;
    private String validUntil;
    private String cvv;
    private String crdHolder;
    private String value;
    private String crdType;

    //Constructor
    public Payment(String crdNumber, String validUntil, String cvv, String crdHolder, String value, String crdType) {
        this.crdNumber = crdNumber;
        this.validUntil = validUntil;
        this.cvv = cvv;
        this.crdHolder = crdHolder;
        this.value =value;
        this.crdType =crdType;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCrdType() {
        return crdType;
    }

    public void setCrdType(String value) {
        this.crdType = crdType;
    }
}
