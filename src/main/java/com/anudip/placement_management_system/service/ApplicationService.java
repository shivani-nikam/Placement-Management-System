package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse create(ApplicationRequest request);
    ApplicationResponse getById(Long id);
    List<ApplicationResponse> getAll();
    List<ApplicationResponse> getByStudent(Long studentId);
    List<ApplicationResponse> getByJob(Long jobId);
    ApplicationResponse updateStatus(Long id, ApplicationRequest request);
    void delete(Long id);
}
