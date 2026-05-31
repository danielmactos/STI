package com.informactos.its.dto;

import java.time.LocalDateTime;

public class RequestListOutput {

    private Long id;
    private String protocol;
    private String customerName;
    private String customerContact;
    private String city;
    private String address;
    private String neighborhood;
    private String referencePoint;
    private String description;
    private String status;

    private Long assistantId;
    private String assistantName;
    private Long technicianId;
    private String technicianName;
    private String triageNote;
    private LocalDateTime assignedAt;
    private LocalDateTime finishedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RequestListOutput() {
    }

    public Long getId() {
        return id;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContact() {
        return customerContact;
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

    public String getStatus() {
        return status;
    }

    public Long getAssistantId() {
        return assistantId;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public String getTriageNote() {
        return triageNote;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssistantId(Long assistantId) {
        this.assistantId = assistantId;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public void setTriageNote(String triageNote) {
        this.triageNote = triageNote;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}