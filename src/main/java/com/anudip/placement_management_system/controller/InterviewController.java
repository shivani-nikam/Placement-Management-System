package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterviewResponse create(
            @Valid @RequestBody InterviewRequest request) {
        return interviewService.create(request);
    }

    @GetMapping
    public List<InterviewResponse> getAll(
            @RequestParam(required = false) Long applicationId) {

        if (applicationId != null) {
            return interviewService.getByApplication(applicationId);
        }

        return interviewService.getAll();
    }

    @GetMapping("/{id}")
    public InterviewResponse getById(@PathVariable Long id) {
        return interviewService.getById(id);
    }

    @PutMapping("/{id}")
    public InterviewResponse update(
            @PathVariable Long id,
            @Valid @RequestBody InterviewRequest request) {
        return interviewService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        interviewService.delete(id);
    }
}
