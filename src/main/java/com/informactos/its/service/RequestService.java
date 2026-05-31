package com.informactos.its.service;

import com.informactos.its.dto.OpenRequestInput;
import com.informactos.its.dto.OpenRequestOutput;
import com.informactos.its.dto.RequestListOutput;
import com.informactos.its.dto.UpdateRequestStatusInput;
import com.informactos.its.model.Customer;
import com.informactos.its.model.Locality;
import com.informactos.its.model.Professional;
import com.informactos.its.model.ProfessionalRole;
import com.informactos.its.model.Request;
import com.informactos.its.model.RequestStatus;
import com.informactos.its.repository.CustomerRepository;
import com.informactos.its.repository.LocalityRepository;
import com.informactos.its.repository.ProfessionalRepository;
import com.informactos.its.repository.RequestRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final CustomerRepository customerRepository;
    private final LocalityRepository localityRepository;
    private final ProfessionalRepository professionalRepository;

    public RequestService(RequestRepository requestRepository,
                          CustomerRepository customerRepository,
                          LocalityRepository localityRepository,
                          ProfessionalRepository professionalRepository) {
        this.requestRepository = requestRepository;
        this.customerRepository = customerRepository;
        this.localityRepository = localityRepository;
        this.professionalRepository = professionalRepository;
    }

    private String generateProtocol() {
        return "Protocolo " + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Transactional
    public OpenRequestOutput openRequest(OpenRequestInput input) {
        Customer customer = new Customer();
        customer.setName(input.getCustomerName());
        customer.setContact(input.getCustomerContact());

        Locality locality = new Locality();
        locality.setCity(input.getCity());
        locality.setAddress(input.getAddress());
        locality.setNeighborhood(input.getNeighborhood());
        locality.setReferencePoint(input.getReferencePoint());

        Customer savedCustomer = customerRepository.save(customer);
        Locality savedLocality = localityRepository.save(locality);

        Request request = new Request();
        request.setProtocol(generateProtocol());
        request.setCustomer(savedCustomer);
        request.setLocality(savedLocality);
        request.setDescription(input.getDescription());
        request.setStatus(RequestStatus.Open);

        Request savedRequest = requestRepository.save(request);

        OpenRequestOutput output = new OpenRequestOutput();
        output.setProtocol(savedRequest.getProtocol());
        output.setStatus(savedRequest.getStatus().name());

        return output;
    }

    @Transactional(readOnly = true)
    public List<RequestListOutput> listRequests() {
        return requestRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::toListOutput)
                .toList();
    }

    @Transactional
    public RequestListOutput updateStatus(Long id, UpdateRequestStatusInput input) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

        RequestStatus status = parseStatus(input.getStatus());

        applyAssistant(request, input.getAssistantId());
        applyTechnician(request, input.getTechnicianId());
        applyTriageNote(request, input.getTriageNote());

        if (status == RequestStatus.Running) {
            validateRunningRequest(request);

            if (request.getAssignedAt() == null) {
                request.setAssignedAt(LocalDateTime.now());
            }
        }

        if (status == RequestStatus.Finished) {
            validateFinishedRequest(request);

            if (request.getFinishedAt() == null) {
                request.setFinishedAt(LocalDateTime.now());
            }
        }

        request.setStatus(status);

        Request updatedRequest = requestRepository.save(request);

        return toListOutput(updatedRequest);
    }

    private void applyAssistant(Request request, Long assistantId) {
        if (assistantId == null) {
            return;
        }

        Professional assistant = professionalRepository.findById(assistantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assistente não encontrada."));

        if (assistant.getRole() != ProfessionalRole.Assistant) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O profissional selecionado não é assistente.");
        }

        request.setAssistant(assistant);
    }

    private void applyTechnician(Request request, Long technicianId) {
        if (technicianId == null) {
            return;
        }

        Professional technician = professionalRepository.findById(technicianId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado."));

        if (technician.getRole() != ProfessionalRole.Technician) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O profissional selecionado não é técnico.");
        }

        request.setTechnician(technician);
    }

    private void applyTriageNote(Request request, String triageNote) {
        if (triageNote == null) {
            return;
        }

        request.setTriageNote(triageNote.trim());
    }

    private void validateRunningRequest(Request request) {
        if (request.getAssistant() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecione a assistente responsável pela triagem.");
        }

        if (request.getTechnician() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecione o técnico responsável pelo atendimento.");
        }
    }

    private void validateFinishedRequest(Request request) {
        if (request.getAssistant() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecione a assistente responsável antes de concluir o chamado.");
        }
    }

    private RequestStatus parseStatus(String status) {
        try {
            return RequestStatus.valueOf(status);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status inválido. Use Open, Running ou Finished."
            );
        }
    }

    private RequestListOutput toListOutput(Request request) {
        RequestListOutput output = new RequestListOutput();

        output.setId(request.getId());
        output.setProtocol(request.getProtocol());
        output.setCustomerName(request.getCustomer().getName());
        output.setCustomerContact(request.getCustomer().getContact());
        output.setCity(request.getLocality().getCity());
        output.setAddress(request.getLocality().getAddress());
        output.setNeighborhood(request.getLocality().getNeighborhood());
        output.setReferencePoint(request.getLocality().getReferencePoint());
        output.setDescription(request.getDescription());
        output.setStatus(request.getStatus().name());

        if (request.getAssistant() != null) {
            output.setAssistantId(request.getAssistant().getId());
            output.setAssistantName(request.getAssistant().getName());
        }

        if (request.getTechnician() != null) {
            output.setTechnicianId(request.getTechnician().getId());
            output.setTechnicianName(request.getTechnician().getName());
        }

        output.setTriageNote(request.getTriageNote());
        output.setAssignedAt(request.getAssignedAt());
        output.setFinishedAt(request.getFinishedAt());
        output.setCreatedAt(request.getCreatedAt());
        output.setUpdatedAt(request.getUpdatedAt());

        return output;
    }
}