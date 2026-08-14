package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Placement;
import com.anudip.placement_management_system.enums.PlacementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlacementRepository extends JpaRepository<Placement, Long> {
    Optional<Placement> findByStudentId(Long studentId);
    List<Placement> findByCompanyId(Long companyId);
    List<Placement> findByStatus(PlacementStatus status);
    boolean existsByStudentId(Long studentId);
    long countByStatus(PlacementStatus status);
}
