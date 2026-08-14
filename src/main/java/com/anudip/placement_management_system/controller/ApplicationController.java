package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse create(
            @Valid @RequestBody ApplicationRequest request) {
        return applicationService.create(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAll(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long jobId) {

        if (studentId != null) {
            return applicationService.getByStudent(studentId);
        }

        if (jobId != null) {
            return applicationService.getByJob(jobId);
        }

        return applicationService.getAll();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getById(@PathVariable Long id) {
        return applicationService.getById(id);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationRequest request) {
        return applicationService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        applicationService.delete(id);
    }
}
