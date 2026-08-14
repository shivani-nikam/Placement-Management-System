package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());
        response.setAppliedDate(application.getAppliedDate());
        response.setStatus(application.getStatus());

        if (application.getStudent() != null) {
            response.setStudentId(application.getStudent().getId());
            response.setStudentName(application.getStudent().getName());
        }

        if (application.getJob() != null) {
            response.setJobId(application.getJob().getId());
            response.setJobTitle(application.getJob().getTitle());

            if (application.getJob().getCompany() != null) {
                response.setCompanyId(application.getJob().getCompany().getId());
                response.setCompanyName(application.getJob().getCompany().getName());
            }
        }

        return response;
    }
}
