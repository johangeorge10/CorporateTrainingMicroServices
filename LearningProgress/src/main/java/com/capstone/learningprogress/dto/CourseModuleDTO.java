package com.capstone.learningprogress.dto;

import java.util.UUID;

public class CourseModuleDTO {
    public UUID id;

    //For fall back method
    public String message;
    public CourseModuleDTO(String message) {
    	this.message=message;
    }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
    
}