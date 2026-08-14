package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {
        return dashboardService.getDashboardStatistics();
    }
}
