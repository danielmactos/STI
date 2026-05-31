package com.informactos.its.controller;

import com.informactos.its.dto.OpenRequestInput;
import com.informactos.its.dto.OpenRequestOutput;
import com.informactos.its.dto.RequestListOutput;
import com.informactos.its.dto.UpdateRequestStatusInput;
import com.informactos.its.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/open")
    public OpenRequestOutput open(@Valid @RequestBody OpenRequestInput input) {
        return requestService.openRequest(input);
    }

    @GetMapping
    public List<RequestListOutput> list() {
        return requestService.listRequests();
    }

    @PatchMapping("/{id}/status")
    public RequestListOutput updateStatus(@PathVariable Long id,
                                          @Valid @RequestBody UpdateRequestStatusInput input) {
        return requestService.updateStatus(id, input);
    }
}