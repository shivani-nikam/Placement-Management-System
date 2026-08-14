package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.entity.Interview;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import com.anudip.placement_management_system.enums.InterviewStatus;
import com.anudip.placement_management_system.exception.ApplicationNotFoundException;
import com.anudip.placement_management_system.mapper.InterviewMapper;
import com.anudip.placement_management_system.repository.ApplicationRepository;
import com.anudip.placement_management_system.repository.InterviewRepository;
import com.anudip.placement_management_system.service.InterviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewMapper interviewMapper;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            ApplicationRepository applicationRepository,
            InterviewMapper interviewMapper) {
        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
        this.interviewMapper = interviewMapper;
    }

    @Override
    public InterviewResponse create(InterviewRequest request) {
        Application application = findApplication(request.getApplicationId());

        Interview interview = new Interview();
        copy(request, interview);
        interview.setApplication(application);

        if (request.getStatus() == null) {
            interview.setResult(InterviewStatus.PENDING);
        }

        application.setStatus(ApplicationStatus.INTERVIEW);

        return interviewMapper.toResponse(
                interviewRepository.save(interview));
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewResponse getById(Long id) {
        return interviewMapper.toResponse(
                findInterview(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterviewResponse> getAll() {
        return interviewRepository.findAll()
                .stream()
                .map(interviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterviewResponse> getByApplication(
            Long applicationId) {

        findApplication(applicationId);

        return interviewRepository
                .findByApplicationId(applicationId)
                .stream()
                .map(interviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewResponse update(
            Long id,
            InterviewRequest request) {

        Interview interview = findInterview(id);
        Application application =
                findApplication(request.getApplicationId());

        copy(request, interview);
        interview.setApplication(application);

        return interviewMapper.toResponse(
                interviewRepository.save(interview));
    }

    @Override
    public void delete(Long id) {
        interviewRepository.delete(findInterview(id));
    }

    private void copy(
            InterviewRequest request,
            Interview interview) {

        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setRound(request.getRound());
        interview.setFeedback(request.getFeedback());

        if (request.getStatus() != null) {
            interview.setResult(request.getStatus());
        }
    }

    private Interview findInterview(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Interview not found with id: " + id));
    }

    private Application findApplication(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(id));
    }
}
