package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.job.JobResponse;
import com.anudip.placement_management_system.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobResponse toResponse(Job job) {
        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setPackageAmount(job.getPackageAmount());
        response.setMinimumCgpa(job.getMinimumCgpa());
        response.setDeadline(job.getDeadline());
        response.setStatus(job.getStatus());

        if (job.getCompany() != null) {
            response.setCompanyId(job.getCompany().getId());
            response.setCompanyName(job.getCompany().getName());
        }

        return response;
    }
}
