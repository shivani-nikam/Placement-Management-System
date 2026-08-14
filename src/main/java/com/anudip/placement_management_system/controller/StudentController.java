package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;
import com.anudip.placement_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(
            @Valid @RequestBody StudentRequest request) {
        return studentService.create(request);
    }

    @GetMapping
    public List<StudentResponse> getAll(
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) Double minCgpa) {

        if (branch != null) {
            return studentService.getByBranch(branch);
        }

        if (minCgpa != null) {
            return studentService.getByMinimumCgpa(minCgpa);
        }

        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
