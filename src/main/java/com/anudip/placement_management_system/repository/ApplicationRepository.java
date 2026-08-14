package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);
    Optional<Application> findByStudentIdAndJobId(Long studentId, Long jobId);
    List<Application> findByStudentId(Long studentId);
    List<Application> findByJobId(Long jobId);
    List<Application> findByStatus(ApplicationStatus status);
    long countByStatus(ApplicationStatus status);
}
