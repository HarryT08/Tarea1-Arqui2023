package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;

public interface DashboardService {

    public int getTotalStudents();

    public int getTotalTeachers();

    public int getTotalClassrooms();

    public List<Course> getCoursesByTeacher(String username);

    public List<Subject> getSubjectsByTeacher(String username);
    
}
