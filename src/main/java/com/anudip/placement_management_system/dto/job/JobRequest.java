package com.anudip.placement_management_system.dto.job;

import com.anudip.placement_management_system.enums.JobStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    private String description;

    @NotNull(message = "Package amount is required")
    @PositiveOrZero(message = "Package amount cannot be negative")
    private Double packageAmount;

    @NotNull(message = "Minimum CGPA is required")
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double minimumCgpa;

    @NotNull(message = "Deadline is required")
    private LocalDate deadline;

    private JobStatus status;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Double packageAmount) { this.packageAmount = packageAmount; }

    public Double getMinimumCgpa() { return minimumCgpa; }
    public void setMinimumCgpa(Double minimumCgpa) { this.minimumCgpa = minimumCgpa; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}
