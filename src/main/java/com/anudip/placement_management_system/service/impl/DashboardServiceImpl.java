package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.enums.ApplicationStatus;
import com.anudip.placement_management_system.enums.PlacementStatus;
import com.anudip.placement_management_system.repository.*;
import com.anudip.placement_management_system.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final PlacementRepository placementRepository;

    public DashboardServiceImpl(
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            PlacementRepository placementRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.placementRepository = placementRepository;
    }

    @Override
    public Map<String, Object> getDashboardStatistics() {
        Map<String, Object> stats = new LinkedHashMap<>();

        stats.put("totalStudents", studentRepository.count());
        stats.put("totalCompanies", companyRepository.count());
        stats.put("totalJobs", jobRepository.count());
        stats.put("totalApplications", applicationRepository.count());

        stats.put(
                "selectedApplications",
                applicationRepository.countByStatus(
                        ApplicationStatus.SELECTED));

        stats.put(
                "totalPlacements",
                placementRepository.count());

        stats.put(
                "joinedPlacements",
                placementRepository.countByStatus(
                        PlacementStatus.JOINED));

        return stats;
    }
}
