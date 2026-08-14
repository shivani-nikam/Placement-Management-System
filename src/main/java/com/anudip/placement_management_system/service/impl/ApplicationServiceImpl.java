package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import com.anudip.placement_management_system.enums.JobStatus;
import com.anudip.placement_management_system.exception.*;
import com.anudip.placement_management_system.mapper.ApplicationMapper;
import com.anudip.placement_management_system.repository.ApplicationRepository;
import com.anudip.placement_management_system.repository.JobRepository;
import com.anudip.placement_management_system.repository.StudentRepository;
import com.anudip.placement_management_system.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository,
            ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationResponse create(ApplicationRequest request) {
        Student student = findStudent(request.getStudentId());
        Job job = findJob(request.getJobId());

        if (applicationRepository.existsByStudentIdAndJobId(
                student.getId(), job.getId())) {
            throw new DuplicateApplicationException(
                    student.getId(), job.getId());
        }

        if (student.getStatus() == null ||
                !"ACTIVE".equals(student.getStatus().name())) {
            throw new IneligibleStudentException(
                    "Only active students can apply");
        }

        if (job.getStatus() != JobStatus.ACTIVE) {
            throw new IneligibleStudentException(
                    "Applications are allowed only for active jobs");
        }

        if (student.getCgpa() < job.getMinimumCgpa()) {
            throw new IneligibleStudentException(
                    "Student CGPA does not satisfy the job requirement");
        }

        if (LocalDate.now().isAfter(job.getDeadline())) {
            throw new IneligibleStudentException(
                    "Application deadline has passed");
        }

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setAppliedDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);

        return applicationMapper.toResponse(
                applicationRepository.save(application));
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationResponse getById(Long id) {
        return applicationMapper.toResponse(findApplication(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponse> getByStudent(Long studentId) {
        findStudent(studentId);

        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponse> getByJob(Long jobId) {
        findJob(jobId);

        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse updateStatus(
            Long id,
            ApplicationRequest request) {

        Application application = findApplication(id);

        if (request.getStatus() != null) {
            application.setStatus(request.getStatus());
        }

        return applicationMapper.toResponse(
                applicationRepository.save(application));
    }

    @Override
    public void delete(Long id) {
        applicationRepository.delete(findApplication(id));
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    private Job findJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    private Application findApplication(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
    }
}
