package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.model.repository.ClassroomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClassroomserviceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public void addClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void updateClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Page<Classroom> getAllClassrooms(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    @Override
    public Classroom getClassroomById(long id) {
        return classroomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }
    
}
