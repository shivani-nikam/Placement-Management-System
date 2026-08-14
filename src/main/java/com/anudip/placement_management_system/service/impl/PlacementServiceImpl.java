package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.entity.*;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import com.anudip.placement_management_system.exception.*;
import com.anudip.placement_management_system.mapper.PlacementMapper;
import com.anudip.placement_management_system.repository.*;
import com.anudip.placement_management_system.service.PlacementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository placementRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final PlacementMapper placementMapper;

    public PlacementServiceImpl(
            PlacementRepository placementRepository,
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            PlacementMapper placementMapper) {
        this.placementRepository = placementRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.placementMapper = placementMapper;
    }

    @Override
    public PlacementResponse create(PlacementRequest request) {
        Student student = findStudent(request.getStudentId());
        Company company = findCompany(request.getCompanyId());
        Job job = findJob(request.getJobId());

        if (placementRepository.existsByStudentId(student.getId())) {
            throw new IllegalArgumentException(
                    "Student already has a placement");
        }

        Application application =
                applicationRepository
                        .findByStudentIdAndJobId(
                                student.getId(), job.getId())
                        .orElseThrow(() ->
                                new IneligibleStudentException(
                                        "Student has not applied for this job"));

        if (application.getStatus() != ApplicationStatus.SELECTED) {
            throw new IneligibleStudentException(
                    "Placement can be created only for a selected application");
        }

        Placement placement = new Placement();
        copy(request, placement);
        placement.setStudent(student);
        placement.setCompany(company);
        placement.setJob(job);

        return placementMapper.toResponse(
                placementRepository.save(placement));
    }

    @Override
    @Transactional(readOnly = true)
    public PlacementResponse getById(Long id) {
        return placementMapper.toResponse(findPlacement(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlacementResponse> getAll() {
        return placementRepository.findAll()
                .stream()
                .map(placementMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlacementResponse> getByCompany(Long companyId) {
        findCompany(companyId);

        return placementRepository.findByCompanyId(companyId)
                .stream()
                .map(placementMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlacementResponse update(
            Long id,
            PlacementRequest request) {

        Placement placement = findPlacement(id);

        placement.setJobRole(request.getJobRole());
        placement.setPackageAmount(request.getPackageAmount());
        placement.setJoiningDate(request.getJoiningDate());

        if (request.getStatus() != null) {
            placement.setStatus(request.getStatus());
        }

        return placementMapper.toResponse(
                placementRepository.save(placement));
    }

    @Override
    public void delete(Long id) {
        placementRepository.delete(findPlacement(id));
    }

    private void copy(
            PlacementRequest request,
            Placement placement) {

        placement.setJobRole(request.getJobRole());
        placement.setPackageAmount(request.getPackageAmount());
        placement.setJoiningDate(request.getJoiningDate());

        if (request.getStatus() != null) {
            placement.setStatus(request.getStatus());
        }
    }

    private Placement findPlacement(Long id) {
        return placementRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Placement not found with id: " + id));
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    private Company findCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    private Job findJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }
}
