package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.repository.ClassroomRepository;
import com.rmiranda.schoolmanagement.model.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private RoleRepository roleRepository;

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
    
}
