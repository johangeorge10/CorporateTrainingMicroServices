package com.capstone.learningprogress.config;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseInitConfig {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createStoredProcedure() {

        String dropProc = """
            DROP PROCEDURE IF EXISTS count_completed_by_user_and_course;
        """;

        String createProc = """
            CREATE PROCEDURE count_completed_by_user_and_course(
                IN  p_user_id CHAR(36),
                IN  p_course_id CHAR(36),
                OUT p_completed_count BIGINT
            )
            BEGIN
                SELECT COUNT(*)
                INTO p_completed_count
                FROM module_progress
                WHERE user_id = p_user_id
                  AND course_id = p_course_id
                  AND completed = true;
            END
        """;

        jdbcTemplate.execute(dropProc);
        jdbcTemplate.execute(createProc);

        System.out.println("Stored procedure initialized successfully");
    }
}
