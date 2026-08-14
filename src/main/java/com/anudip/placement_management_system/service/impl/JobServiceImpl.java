package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.job.JobRequest;
import com.anudip.placement_management_system.dto.job.JobResponse;
import com.anudip.placement_management_system.entity.Company;
import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.enums.JobStatus;
import com.anudip.placement_management_system.exception.CompanyNotFoundException;
import com.anudip.placement_management_system.exception.JobNotFoundException;
import com.anudip.placement_management_system.mapper.JobMapper;
import com.anudip.placement_management_system.repository.CompanyRepository;
import com.anudip.placement_management_system.repository.JobRepository;
import com.anudip.placement_management_system.service.JobService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(
            JobRepository jobRepository,
            CompanyRepository companyRepository,
            JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public JobResponse create(JobRequest request) {
        Company company = findCompany(request.getCompanyId());

        Job job = new Job();
        copy(request, job);
        job.setCompany(company);

        return jobMapper.toResponse(jobRepository.save(job));
    }

    @Override
    @Transactional(readOnly = true)
    public JobResponse getById(Long id) {
        return jobMapper.toResponse(findJob(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobResponse> getJobs(
            String keyword,
            JobStatus status,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort.Direction sortDirection =
                "desc".equalsIgnoreCase(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                Math.max(size, 1),
                Sort.by(sortDirection, sortBy)
        );

        Page<Job> jobs;

        boolean hasKeyword = keyword != null && !keyword.isBlank();

        if (hasKeyword && status != null) {
            jobs = jobRepository.findByTitleContainingIgnoreCaseAndStatus(
                    keyword.trim(), status, pageable);
        } else if (hasKeyword) {
            jobs = jobRepository.findByTitleContainingIgnoreCase(
                    keyword.trim(), pageable);
        } else if (status != null) {
            jobs = jobRepository.findByStatus(status, pageable);
        } else {
            jobs = jobRepository.findAll(pageable);
        }

        return jobs.map(jobMapper::toResponse);
    }

    @Override
    public JobResponse update(Long id, JobRequest request) {
        Job job = findJob(id);
        Company company = findCompany(request.getCompanyId());

        copy(request, job);
        job.setCompany(company);

        return jobMapper.toResponse(jobRepository.save(job));
    }

    @Override
    public void delete(Long id) {
        jobRepository.delete(findJob(id));
    }

    private void copy(JobRequest request, Job job) {
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setPackageAmount(request.getPackageAmount());
        job.setMinimumCgpa(request.getMinimumCgpa());
        job.setDeadline(request.getDeadline());

        if (request.getStatus() != null) {
            job.setStatus(request.getStatus());
        }
    }

    private Job findJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    private Company findCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }
}
