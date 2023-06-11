package com.md.ReactSpringbootWithNeo4j.objects;

import com.md.ReactSpringbootWithNeo4j.models.Course;

public class CourseEnrollmentDTO {

    private String username;
    private String name;
    private Course course;

    public CourseEnrollmentDTO(String username, String name, Course course) {
        this.username = username;
        this.name = name;
        this.course = course;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
