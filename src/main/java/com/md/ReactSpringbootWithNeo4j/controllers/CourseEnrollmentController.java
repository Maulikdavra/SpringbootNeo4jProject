package com.md.ReactSpringbootWithNeo4j.controllers;

import com.md.ReactSpringbootWithNeo4j.models.Course;
import com.md.ReactSpringbootWithNeo4j.objects.CourseDTO;
import com.md.ReactSpringbootWithNeo4j.objects.CourseEnrollmentDTO;
import com.md.ReactSpringbootWithNeo4j.queryresults.CourseEnrollmentQueryResult;
import com.md.ReactSpringbootWithNeo4j.requests.CourseEnrollemtRequest;
import com.md.ReactSpringbootWithNeo4j.services.CourseEnrollmentService;
import com.md.ReactSpringbootWithNeo4j.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    private final LessonService lessonService;

    @Autowired
    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService, LessonService lessonService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.lessonService = lessonService;
    }

    @PostMapping("/createEnrollment")
    public ResponseEntity<CourseEnrollmentDTO> enrollCourses(@RequestBody CourseEnrollemtRequest courseEnrollemtRequest, Principal principal){
        CourseEnrollmentQueryResult courseEnrollmentQueryResult = courseEnrollmentService.createEnrollment(principal.getName(), courseEnrollemtRequest.getIdentifier());
        CourseEnrollmentDTO responseEnrollment = new CourseEnrollmentDTO(courseEnrollmentQueryResult.getUser().getUsername(),
                                                                        courseEnrollmentQueryResult.getUser().getName(),
                                                                        courseEnrollmentQueryResult.getCourse());
        return new ResponseEntity<>(responseEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/getEnrolledUser")
    public ResponseEntity<List<CourseDTO>> enrollments(Principal principal){
        List<Course> courses = courseEnrollmentService.getAllEnrolledCoursesByUsername(principal.getName());

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO();

                    responseCourse.setIdentifier(course.getIdentifier());
                    responseCourse.setTitle(course.getTitle());
                    responseCourse.setTeacher(course.getTeacher());
                    responseCourse.setLesson(lessonService.getAllLessonByCourseIdentifier(course.getIdentifier()));
                    responseCourse.setEnrolled(true);

                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

}
