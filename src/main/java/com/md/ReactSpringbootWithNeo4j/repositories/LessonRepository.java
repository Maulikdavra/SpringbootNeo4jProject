package com.md.ReactSpringbootWithNeo4j.repositories;

import com.md.ReactSpringbootWithNeo4j.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {

    /**
     *
     * @param identifier below, a query will execute a Course node having an identifier which we have provided via method parameter.
     *                   and then the query will fetch/find all the lessons belong to this specific course identifier, and in the end it returns all the lessons
     *                   *Note that in the query, Course and Lesson are the nodes, r and lessons are more of like variables used to store data in it.
     * @return all the relationship belonging to specific @path-variable (In our case, it's an identifier
     *
     * <p>
     * The reason why we have created query in this separate repository is that we don't want to have this query being executed in the Course model because
     * if we try to execute all courses, we will always have lessons coming with them as a relationship, this could be a problem when dealing
     * with thousands of courses and lessons.
     *
     * In short, this is a concept of lazy fetching.
     */
    @Query("Match (:Course {identifier:$identifier})<-[r:BELONGS_TO]-(lessons:Lesson) RETURN lessons")
    List<Lesson> findLessonsByCourseIdentifier(String identifier);
}
