package com.informactos.its.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateRequestStatusInput {

    @NotBlank(message = "O status é obrigatório.")
    private String status;

    @Positive(message = "O identificador da assistente deve ser maior que zero.")
    private Long assistantId;

    @Positive(message = "O identificador do técnico deve ser maior que zero.")
    private Long technicianId;

    @Size(max = 1000, message = "A observação de triagem deve ter no máximo 1000 caracteres.")
    private String triageNote;

    public UpdateRequestStatusInput() {
    }

    public String getStatus() {
        return status;
    }

    public Long getAssistantId() {
        return assistantId;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getTriageNote() {
        return triageNote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssistantId(Long assistantId) {
        this.assistantId = assistantId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public void setTriageNote(String triageNote) {
        this.triageNote = triageNote;
    }
}