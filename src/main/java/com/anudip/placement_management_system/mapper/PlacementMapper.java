package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.entity.Placement;
import org.springframework.stereotype.Component;

@Component
public class PlacementMapper {

    public PlacementResponse toResponse(Placement placement) {
        PlacementResponse response = new PlacementResponse();

        response.setId(placement.getId());
        response.setJobRole(placement.getJobRole());
        response.setPackageAmount(placement.getPackageAmount());
        response.setJoiningDate(placement.getJoiningDate());
        response.setStatus(placement.getStatus());

        if (placement.getStudent() != null) {
            response.setStudentId(placement.getStudent().getId());
            response.setStudentName(placement.getStudent().getName());
        }

        if (placement.getCompany() != null) {
            response.setCompanyId(placement.getCompany().getId());
            response.setCompanyName(placement.getCompany().getName());
        }

        if (placement.getJob() != null) {
            response.setJobId(placement.getJob().getId());
            response.setJobTitle(placement.getJob().getTitle());
        }

        return response;
    }
}
