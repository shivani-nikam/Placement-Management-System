package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Interview;
import com.anudip.placement_management_system.enums.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByApplicationId(Long applicationId);
    List<Interview> findByResult(InterviewStatus status);
}
