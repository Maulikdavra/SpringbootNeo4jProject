package com.md.ReactSpringbootWithNeo4j.services;

import com.md.ReactSpringbootWithNeo4j.models.Lesson;
import com.md.ReactSpringbootWithNeo4j.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessonByCourseIdentifier(String identifier){
        return lessonRepository.findLessonsByCourseIdentifier(identifier);
    }
}
