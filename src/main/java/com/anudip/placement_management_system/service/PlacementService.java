package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;

import java.util.List;

public interface PlacementService {
    PlacementResponse create(PlacementRequest request);
    PlacementResponse getById(Long id);
    List<PlacementResponse> getAll();
    List<PlacementResponse> getByCompany(Long companyId);
    PlacementResponse update(Long id, PlacementRequest request);
    void delete(Long id);
}
