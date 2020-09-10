package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;


import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Classroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class ClassroomServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ClassroomService classroomService;

    @Test
    public void testAddClassroom() {
        Classroom classroom = new Classroom();
        classroom.setName("Salon 01");

        classroomService.addClassroom(classroom);

        Classroom dbClassroom = em.find(Classroom.class, classroom.getId());
        
        assertTrue(dbClassroom.getName().equals(classroom.getName()));
    }

    @Test
    public void testUpdateClassroom() {
        Classroom classroom = em.find(Classroom.class, Long.valueOf(1));

        classroom.setName("updated classrooom");

        classroomService.updateClassroom(classroom);

        Classroom updatdClassroom = em.find(Classroom.class, Long.valueOf(1));

        assertTrue(classroom.getName().equals(updatdClassroom.getName()));
    }

    @Test
    public void testGetAllClassrooms() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Classroom> classrooms = classroomService.getAllClassrooms(pageable);

        assertTrue(classrooms.getNumberOfElements() > 0);
    }

    @Test
    public void testGetClassroomById() {
        Classroom classroom = classroomService.getClassroomById(Long.valueOf(1));

        Classroom dbClassroom = em.find(Classroom.class, Long.valueOf(1));

        assertTrue(classroom.getName().equals(dbClassroom.getName()));
    }
    
}
