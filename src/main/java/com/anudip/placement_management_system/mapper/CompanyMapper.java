package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;
import com.anudip.placement_management_system.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setEmail(company.getEmail());
        response.setPhone(company.getPhone());
        response.setAddress(company.getAddress());
        response.setStatus(company.getStatus());

        return response;
    }

    public void updateEntity(Company company, CompanyRequest request) {
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setAddress(request.getAddress());

        if (request.getStatus() != null) {
            company.setStatus(request.getStatus());
        }
    }
}
