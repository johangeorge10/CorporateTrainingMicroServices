package com.capstone.learningprogress.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@NamedStoredProcedureQuery(
	    name = "ModuleProgress.countCompleted",
	    procedureName = "count_completed_by_user_and_course",
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "p_user_id", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "p_course_id", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_completed_count", type = Long.class)
	    }
	)
@Entity
@Table(
    name = "module_progress",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "courseId", "moduleId"})
    }
)
public class ModuleProgress {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID courseId;

    @Column(nullable = false)
    private UUID moduleId;

    @Column(nullable = false)
    private Boolean completed;

    private LocalDateTime completedAt;

    // getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }

    public UUID getModuleId() { return moduleId; }
    public void setModuleId(UUID moduleId) { this.moduleId = moduleId; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
