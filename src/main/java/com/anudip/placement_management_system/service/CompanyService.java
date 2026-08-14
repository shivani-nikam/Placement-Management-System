package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse create(CompanyRequest request);
    CompanyResponse getById(Long id);
    List<CompanyResponse> getAll();
    CompanyResponse update(Long id, CompanyRequest request);
    void delete(Long id);
}
