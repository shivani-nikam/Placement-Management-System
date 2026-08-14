package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;

import java.util.List;

public interface InterviewService {
    InterviewResponse create(InterviewRequest request);
    InterviewResponse getById(Long id);
    List<InterviewResponse> getAll();
    List<InterviewResponse> getByApplication(Long applicationId);
    InterviewResponse update(Long id, InterviewRequest request);
    void delete(Long id);
}
