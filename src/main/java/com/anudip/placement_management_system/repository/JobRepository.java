package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(Long companyId);
    List<Job> findByStatus(JobStatus status);
    Page<Job> findByStatus(JobStatus status, Pageable pageable);
    Page<Job> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Job> findByTitleContainingIgnoreCaseAndStatus(
            String keyword, JobStatus status, Pageable pageable);
}
