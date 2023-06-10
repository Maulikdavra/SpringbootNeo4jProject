package com.md.ReactSpringbootWithNeo4j.controllers;

import com.md.ReactSpringbootWithNeo4j.models.Course;
import com.md.ReactSpringbootWithNeo4j.objects.CourseDTO;
import com.md.ReactSpringbootWithNeo4j.services.CourseService;
import com.md.ReactSpringbootWithNeo4j.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    private final LessonService lessonService;

    @Autowired
    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> courseIndex(){
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
                    responseCourse.setLesson(lessonService.getAllLessonByCourseIdentifier(course.getIdentifier()));

                    return responseCourse;
                }
        ).toList();
        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetail(@PathVariable String identifier){
        Course course = courseService.getCourseByIdentifier(identifier);
        CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
        responseCourse.setLesson(lessonService.getAllLessonByCourseIdentifier(course.getIdentifier()));
        return new ResponseEntity<>(responseCourse, HttpStatus.OK);
    }
}
