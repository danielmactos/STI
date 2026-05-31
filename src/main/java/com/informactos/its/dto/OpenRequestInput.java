package com.informactos.its.dto;

import jakarta.validation.constraints.NotBlank;

public class OpenRequestInput {

    @NotBlank(message = "Nome completo")
    private String customerName;

    @NotBlank(message = "Contato")
    private String customerContact;

    @NotBlank(message = "Cidade")
    private String city;

    @NotBlank(message = "Endereço")
    private String address;

    @NotBlank(message = "Bairro")
    private String neighborhood;

    private String referencePoint;

    @NotBlank(message = "Descrição")
    private String description;

    public OpenRequestInput() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContact() {
        return  customerContact;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public String getDescription() {
        return description;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}