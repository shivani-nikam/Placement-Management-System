package com.anudip.placement_management_system.dto.placement;

import com.anudip.placement_management_system.enums.PlacementStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class PlacementRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotBlank(message = "Job role is required")
    private String jobRole;

    @NotNull(message = "Package amount is required")
    @PositiveOrZero(message = "Package amount cannot be negative")
    private Double packageAmount;

    private LocalDate joiningDate;
    private PlacementStatus status;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public Double getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Double packageAmount) { this.packageAmount = packageAmount; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    public PlacementStatus getStatus() { return status; }
    public void setStatus(PlacementStatus status) { this.status = status; }
}
