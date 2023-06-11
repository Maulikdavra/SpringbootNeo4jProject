package com.md.ReactSpringbootWithNeo4j.services;

import com.md.ReactSpringbootWithNeo4j.models.Course;
import com.md.ReactSpringbootWithNeo4j.queryresults.CourseEnrollmentQueryResult;
import com.md.ReactSpringbootWithNeo4j.repositories.CourseRepository;
import com.md.ReactSpringbootWithNeo4j.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrollmentService {

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public CourseEnrollmentService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    /**
     *
     * Below method will delegate call to UserRepository to check if the current student is enrolled in a given course.
     *
     * @param username will be received from controller via API response body.
     * @param identifier will be received from controller via API response body.
     * @return boolean
     */
    public Boolean getEnrollmentStatus(String username, String identifier){
        return userRepository.findEnrollmentStatus(username, identifier);
    }

    /**
     *
     * Below method will delegate call to UserRepository to create enrollment for a user.
     *
     * @param username will be received from controller via API response body.
     * @param identifier will be received from controller via API response body.
     * @return objects of User and Course class containing information about user and enrolled courses. stored in CourseEnrollmentQueryResult class
     */
    public CourseEnrollmentQueryResult createEnrollment(String username, String identifier){
        return userRepository.createEnrollmentRelationship(username, identifier);
    }

    /**
     *
     * @param username will be received from controller via API response body.
     * @return courses enrolled by user
     */
    public List<Course> getAllEnrolledCoursesByUsername(String username){
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}
