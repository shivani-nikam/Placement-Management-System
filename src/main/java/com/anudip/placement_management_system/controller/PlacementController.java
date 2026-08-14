package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.service.PlacementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlacementResponse create(
            @Valid @RequestBody PlacementRequest request) {
        return placementService.create(request);
    }

    @GetMapping
    public List<PlacementResponse> getAll(
            @RequestParam(required = false) Long companyId) {

        if (companyId != null) {
            return placementService.getByCompany(companyId);
        }

        return placementService.getAll();
    }

    @GetMapping("/{id}")
    public PlacementResponse getById(@PathVariable Long id) {
        return placementService.getById(id);
    }

    @PutMapping("/{id}")
    public PlacementResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PlacementRequest request) {
        return placementService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        placementService.delete(id);
    }
}
