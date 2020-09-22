package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.repository.CourseRepository;
import com.rmiranda.schoolmanagement.model.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjectsByCourseId(long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        return subjectRepository.findByCourse(course);
    }
    
}
