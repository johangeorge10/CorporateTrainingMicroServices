package com.capstone.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseProgressDTO {

    @JsonProperty("completionPercentage")
    private double completionPercentage;

    // Default Constructor
    public CourseProgressDTO() {}

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}