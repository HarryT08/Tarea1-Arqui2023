package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.ClassroomRepository;
import com.rmiranda.schoolmanagement.model.repository.CourseRepository;
import com.rmiranda.schoolmanagement.model.repository.RoleRepository;
import com.rmiranda.schoolmanagement.model.repository.SubjectRepository;
import com.rmiranda.schoolmanagement.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public int getTotalStudents() {
        return roleRepository.findRoleByName("ROLE_STUDENT").getUsers().size();
    }

    @Override
    public int getTotalTeachers() {
        return roleRepository.findRoleByName("ROLE_TEACHER").getUsers().size();
    }

    @Override
    public int getTotalClassrooms() {
        return classroomRepository.findAll().size();
    }

    @Override
    public List<Course> getCoursesByTeacher(String username) {
        User manager = userRepository.findUSerByUsername(username);
        return courseRepository.findByManager(manager);
    }

    @Override
    public List<Subject> getSubjectsByTeacher(String username) {
        User teacher = userRepository.findUSerByUsername(username);
        return subjectRepository.findByTeacher(teacher);
    }
    
}
