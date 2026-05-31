package com.informactos.its.controller;

import com.informactos.its.dto.ProfessionalInput;
import com.informactos.its.dto.ProfessionalOutput;
import com.informactos.its.service.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    public ProfessionalOutput create(@Valid @RequestBody ProfessionalInput input) {
        return professionalService.create(input);
    }

    @GetMapping
    public List<ProfessionalOutput> list() {
        return professionalService.list();
    }
}