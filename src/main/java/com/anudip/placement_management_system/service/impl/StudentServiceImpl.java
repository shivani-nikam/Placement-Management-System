package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;
import com.anudip.placement_management_system.entity.Skill;
import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.exception.StudentNotFoundException;
import com.anudip.placement_management_system.mapper.StudentMapper;
import com.anudip.placement_management_system.repository.SkillRepository;
import com.anudip.placement_management_system.repository.StudentRepository;
import com.anudip.placement_management_system.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            SkillRepository skillRepository,
            StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.skillRepository = skillRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        if (studentRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = new Student();
        studentMapper.updateEntity(student, request);
        student.setSkills(resolveSkills(request));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getById(Long id) {
        return studentMapper.toResponse(findStudent(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getByBranch(String branch) {
        return studentRepository.findByBranchIgnoreCase(branch)
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getByMinimumCgpa(Double cgpa) {
        return studentRepository.findByCgpaGreaterThanEqual(cgpa)
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = findStudent(id);
        studentMapper.updateEntity(student, request);
        student.setSkills(resolveSkills(request));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        Student student = findStudent(id);
        studentRepository.delete(student);
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    private HashSet<Skill> resolveSkills(StudentRequest request) {
        HashSet<Skill> skills = new HashSet<>();

        if (request.getSkillNames() == null) {
            return skills;
        }

        for (String name : request.getSkillNames()) {
            if (name == null || name.isBlank()) {
                continue;
            }

            String cleanName = name.trim();

            Skill skill = skillRepository.findByNameIgnoreCase(cleanName)
                    .orElseGet(() ->
                            skillRepository.save(new Skill(cleanName)));

            skills.add(skill);
        }

        return skills;
    }
}
