package com.md.ReactSpringbootWithNeo4j.objects;

import com.md.ReactSpringbootWithNeo4j.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

    private final String identifier;
    private final String title;
    private final String teacher;
    private List<Lesson> lesson = new ArrayList<>();

    public CourseDTO(String identifier, String title, String teacher) {
        this.identifier = identifier;
        this.title = title;
        this.teacher = teacher;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }
}
