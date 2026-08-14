package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;
import com.anudip.placement_management_system.entity.Skill;
import com.anudip.placement_management_system.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setCgpa(student.getCgpa());
        response.setBranch(student.getBranch());
        response.setResume(student.getResume());
        response.setStatus(student.getStatus());
        response.setSkills(student.getSkills() == null
                ? Collections.emptyList()
                : student.getSkills().stream()
                .map(Skill::getName)
                .sorted()
                .collect(Collectors.toList()));

        return response;
    }

    public void updateEntity(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCgpa(request.getCgpa());
        student.setBranch(request.getBranch());
        student.setResume(request.getResume());

        if (request.getStatus() != null) {
            student.setStatus(request.getStatus());
        }
    }
}
