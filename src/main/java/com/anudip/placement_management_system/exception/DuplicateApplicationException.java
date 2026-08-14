package com.anudip.placement_management_system.exception;

public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException(Long studentId, Long jobId) {
        super("Student " + studentId + " has already applied for job " + jobId);
    }
}
