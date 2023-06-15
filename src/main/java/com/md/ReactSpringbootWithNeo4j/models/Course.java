package com.md.ReactSpringbootWithNeo4j.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String identifier;
    private String title;
    private String teacher;

    // Below lines will fetch all the relationship belonging to specific @path-variable (In our case, it's an identifier)
    /**
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private List<Lesson> lesson = new ArrayList<>();
    */
    public Course() {
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
}
