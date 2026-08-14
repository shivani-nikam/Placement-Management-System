package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
