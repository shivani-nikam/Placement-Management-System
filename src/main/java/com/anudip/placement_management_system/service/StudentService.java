package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);
    StudentResponse getById(Long id);
    List<StudentResponse> getAll();
    List<StudentResponse> getByBranch(String branch);
    List<StudentResponse> getByMinimumCgpa(Double cgpa);
    StudentResponse update(Long id, StudentRequest request);
    void delete(Long id);
}
