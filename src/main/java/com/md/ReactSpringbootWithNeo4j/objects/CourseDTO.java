package com.md.ReactSpringbootWithNeo4j.objects;

import com.md.ReactSpringbootWithNeo4j.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

    private String identifier;
    private String title;
    private String teacher;
    private List<Lesson> lesson = new ArrayList<>();
    private boolean isEnrolled;

    public CourseDTO() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lessons) {
        this.lesson = lessons;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }


}
