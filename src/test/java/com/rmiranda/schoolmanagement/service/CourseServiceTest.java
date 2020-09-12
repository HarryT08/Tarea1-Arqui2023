package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.entity.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EntityManager em;

    @Test
    public void testAddCourse() {
        Cycle cycle = em.find(Cycle.class, Long.valueOf(1));
        Classroom classroom = em.find(Classroom.class, Long.valueOf(1));
        Role role = em.createQuery("from Role r where r.id = 3", Role.class).getSingleResult();

        Course course = new Course();
        course.setName("Curso test");
        course.setClassroom(classroom);
        course.setCycle(cycle);
        course.setManager(role.getUsers().get(0));

        courseService.addCourse(course);

        Course dbCourse = em.find(Course.class, Long.valueOf(course.getId()));

        assertTrue(course.getName().equals(dbCourse.getName()));

    }

    @Test
    public void testUpdateCourse() {
        Course course = em.find(Course.class, Long.valueOf(1));

        course.setName("updated course");

        courseService.updateCourse(course);

        Course updatedCourse = em.find(Course.class, course.getId());

        assertTrue(course.getName().equals(updatedCourse.getName()));
    }

    @Test
    public void testGetAllCoursesByCycleId() {
        List<Course> courses = courseService.getAllCoursesByCycleId(Long.valueOf(1));

        assertTrue(courses.size() > 0);
    }

}