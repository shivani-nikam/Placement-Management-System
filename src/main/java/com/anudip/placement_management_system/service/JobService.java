package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.job.JobRequest;
import com.anudip.placement_management_system.dto.job.JobResponse;
import com.anudip.placement_management_system.enums.JobStatus;
import org.springframework.data.domain.Page;

public interface JobService {
    JobResponse create(JobRequest request);
    JobResponse getById(Long id);
    Page<JobResponse> getJobs(
            String keyword,
            JobStatus status,
            int page,
            int size,
            String sortBy,
            String direction
    );
    JobResponse update(Long id, JobRequest request);
    void delete(Long id);
}
