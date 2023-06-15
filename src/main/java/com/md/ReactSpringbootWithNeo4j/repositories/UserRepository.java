package com.md.ReactSpringbootWithNeo4j.repositories;

import com.md.ReactSpringbootWithNeo4j.models.User;
import com.md.ReactSpringbootWithNeo4j.queryresults.CourseEnrollmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long>{

    Optional<User> findUserByUsername(String username);

    /**
     *
     * This method finds the enrollment status for a user in a course.
     * The query matches two nodes: a User node and a Course node.
     * The User node is matched by the username property, and the Course node is matched by the identifier property.
     * <p>
     * In short, we are looking for a user and course against the username and identifier that we have from method
     * later checking if there is any outgoing relationship from user to course of a type "ENROLLED_IN"
     * </p>
     * @param username will be provided to query from the method parameter, the method will get it from a database.
     * @param identifier will be provided to query from the method parameter, the identifier will get it from a database
     * @return true or false based on the match found by query
     */
    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrollmentStatus(String username, String identifier);

    /**
     *
     * The Below Query is different from the one above.
     * This method creates an enrollment relationship between a user and a course.
     * The query matches two nodes: a User node and a Course node.
     * The User node is matched by the username property, and the Course node is matched by the identifier property.
     * If the query finds a match, the method creates an ENROLLED_IN relationship between the two nodes.
     * The method returns a CourseEnrollmentQueryResult object, which contains the User node and the Course node.
     *
     * @param username will be provided to query from the method parameter, the method will get it from a database.
     * @param identifier will be provided to query from the method parameter, the identifier will get it from a database.
     * @return two objects, user and identifier
     */
    @Query("Match (user:User), (course:Course) " +
            "WHERE user.username=$username AND course.identifier=$identifier" +
            " CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course")
    CourseEnrollmentQueryResult createEnrollmentRelationship(String username, String identifier);
}
