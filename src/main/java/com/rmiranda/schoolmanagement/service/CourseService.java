package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;

public interface CourseService {

    public void addCourse(Course course);

    public void updateCourse(Course course);

    public List<Course> getAllCoursesByCycleId(long idCycle);

    public List<Course> getAllCoursesByCycle(Cycle cycle);

    public Course getCourseById(long id);

    public void unsubscribeStudent(long courseId, long studentId);

    public void deleteCourseById(long courseId);

    public void deleteCourse(Course course);
    
}
