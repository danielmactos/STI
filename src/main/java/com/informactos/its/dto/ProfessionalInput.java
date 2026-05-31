package com.informactos.its.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfessionalInput {

    @NotBlank(message = "O nome da empresa é obrigatório.")
    private String companyName;

    @NotBlank(message = "O nome do profissional é obrigatório.")
    private String name;

    @NotBlank(message = "A função do profissional é obrigatória.")
    private String role;

    private String specialty;

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}