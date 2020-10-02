package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubjectServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private SubjectService subjectService;

    @Test
    public void testAddSubject() {
        Subject subject = new Subject();

        subject.setName("Matematica");

        Course course = em.find(Course.class, Long.valueOf(1));
        Classroom classroom = em.find(Classroom.class, Long.valueOf(1));
        User teacher = em.find(User.class, Long.valueOf(1));

        //SubjectSchedule schedule = new SubjectSchedule();
        //jschedule.setStartTime(new Time());
        //jschedule.setEndTime(new Date());
        //schedule.setSubject(subject);

        subject.setCourse(course);
        subject.setClassroom(classroom);
        subject.setTeacher(teacher);
        //subject.setSchedule(schedule);
        //TOOD: Fix test

        subjectService.addSubject(subject);

        Subject insertedSubject = em.find(Subject.class, subject.getId());

        assertTrue(subject.getId() == insertedSubject.getId());

    }

    @Test
    public void TestUpdateSubject() {
        Subject subject = em.find(Subject.class, Long.valueOf(1));

        subject.setName("Ciencia");

        subjectService.updateSubject(subject);

        Subject updatedSubject = em.find(Subject.class, subject.getId());

        assertTrue(subject.getName().equals(updatedSubject.getName()));
    }

    @Test
    public void testGetSubjectsByCourseId() {
        Course course = em.find(Course.class, Long.valueOf(1));

        List<Subject> subjects = subjectService.getAllSubjectsByCourseId(course.getId());

        assertTrue(subjects.size() > 0);
    }
    
}
