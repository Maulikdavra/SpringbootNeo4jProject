package com.md.ReactSpringbootWithNeo4j.requests;

public class CourseEnrollemtRequest {

    private String courseIdentifier;

    public CourseEnrollemtRequest() {
    }

    public String getIdentifier() {
        return courseIdentifier;
    }

    public void setIdentifier(String identifier) {
        this.courseIdentifier = identifier;
    }
}
