package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.entity.Interview;
import org.springframework.stereotype.Component;

@Component
public class InterviewMapper {

    public InterviewResponse toResponse(Interview interview) {
        InterviewResponse response = new InterviewResponse();

        response.setId(interview.getId());
        response.setInterviewDate(interview.getInterviewDate());
        response.setInterviewTime(interview.getInterviewTime());
        response.setRound(interview.getRound());
        response.setFeedback(interview.getFeedback());
        response.setStatus(interview.getResult());

        if (interview.getApplication() != null) {
            response.setApplicationId(interview.getApplication().getId());

            if (interview.getApplication().getStudent() != null) {
                response.setStudentId(
                        interview.getApplication().getStudent().getId());
                response.setStudentName(
                        interview.getApplication().getStudent().getName());
            }

            if (interview.getApplication().getJob() != null) {
                response.setJobId(
                        interview.getApplication().getJob().getId());
                response.setJobTitle(
                        interview.getApplication().getJob().getTitle());
            }
        }

        return response;
    }
}
