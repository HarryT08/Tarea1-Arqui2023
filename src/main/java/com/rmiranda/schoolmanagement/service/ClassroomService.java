package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Classroom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassroomService {

    public void addClassroom(Classroom classroom);

    public void updateClassroom(Classroom classroom);

    public Page<Classroom> getAllClassrooms(Pageable pageable);

    public List<Classroom> getAllClassrooms();

    public Classroom getClassroomById(long id);
    
}
