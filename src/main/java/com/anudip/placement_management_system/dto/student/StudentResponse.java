package com.anudip.placement_management_system.dto.student;

import com.anudip.placement_management_system.enums.StudentStatus;

import java.util.List;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Double cgpa;
    private String branch;
    private String resume;
    private StudentStatus status;
    private List<String> skills;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
