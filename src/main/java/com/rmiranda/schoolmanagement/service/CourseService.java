package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;

public interface CourseService {

    public void addCourse(Course course);

    public void updateCourse(Course course);

    public List<Course> getAllCoursesByCycleId(long idCycle);

    public List<Course> getAllCoursesByCycle(Cycle cycle);
    
}
