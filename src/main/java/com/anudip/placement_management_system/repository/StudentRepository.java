package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<Student> findByBranchIgnoreCase(String branch);
    List<Student> findByCgpaGreaterThanEqual(Double cgpa);
}
