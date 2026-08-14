package com.anudip.placement_management_system.dto.interview;

import com.anudip.placement_management_system.enums.InterviewStatus;
import com.anudip.placement_management_system.enums.InterviewStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class InterviewResponse {

    private Long id;
    private LocalDate interviewDate;
    private LocalTime interviewTime;
    private String round;
    private String feedback;
    private InterviewStatus status;
    private Long applicationId;
    private Long studentId;
    private String studentName;
    private Long jobId;
    private String jobTitle;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getInterviewDate() { return interviewDate; }
    public void setInterviewDate(LocalDate interviewDate) { this.interviewDate = interviewDate; }

    public LocalTime getInterviewTime() { return interviewTime; }
    public void setInterviewTime(LocalTime interviewTime) { this.interviewTime = interviewTime; }

    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public InterviewStatus getStatus() { return status; }
    public void setStatus(InterviewStatus status) { this.status = status; }

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}
