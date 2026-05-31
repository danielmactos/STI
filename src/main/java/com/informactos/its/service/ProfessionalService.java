package com.informactos.its.service;

import com.informactos.its.dto.ProfessionalInput;
import com.informactos.its.dto.ProfessionalOutput;
import com.informactos.its.model.Company;
import com.informactos.its.model.Professional;
import com.informactos.its.model.ProfessionalRole;
import com.informactos.its.repository.CompanyRepository;
import com.informactos.its.repository.ProfessionalRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessionalService {

    private final CompanyRepository companyRepository;
    private final ProfessionalRepository professionalRepository;

    public ProfessionalService(CompanyRepository companyRepository,
                               ProfessionalRepository professionalRepository) {
        this.companyRepository = companyRepository;
        this.professionalRepository = professionalRepository;
    }

    @Transactional
    public ProfessionalOutput create(ProfessionalInput input) {
        Company company = companyRepository
                .findByName(input.getCompanyName())
                .orElseGet(() -> createCompany(input.getCompanyName()));

        Professional professional = new Professional();
        professional.setCompany(company);
        professional.setName(input.getName());
        professional.setRole(parseRole(input.getRole()));
        professional.setSpecialty(input.getSpecialty());

        Professional savedProfessional = professionalRepository.save(professional);

        return toOutput(savedProfessional);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalOutput> list() {
        return professionalRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private Company createCompany(String companyName) {
        Company company = new Company();
        company.setName(companyName);
        return companyRepository.save(company);
    }

    private ProfessionalRole parseRole(String role) {
        try {
            return ProfessionalRole.valueOf(role);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Função inválida. Use Assistant ou Technician."
            );
        }
    }

    private ProfessionalOutput toOutput(Professional professional) {
        ProfessionalOutput output = new ProfessionalOutput();

        output.setId(professional.getId());
        output.setCompanyName(professional.getCompany().getName());
        output.setName(professional.getName());
        output.setRole(professional.getRole().name());
        output.setSpecialty(professional.getSpecialty());
        output.setCreatedAt(professional.getCreatedAt());
        output.setUpdatedAt(professional.getUpdatedAt());

        return output;
    }
}