package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.CourseRepository;
import com.rmiranda.schoolmanagement.model.repository.CycleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
	private CycleRepository cycleRepository;
	
	@Override
	public void addCourse(Course course) {
        courseRepository.save(course);
	}

	@Override
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCoursesByCycleId(long idCycle) {
        Cycle cycle = cycleRepository.getOne(idCycle);

        return courseRepository.findByCycle(cycle);
	}

	@Override
	public List<Course> getAllCoursesByCycle(Cycle cycle) {
        return courseRepository.findByCycle(cycle);
	}

	@Override
	public Course getCourseById(long id) {
		return courseRepository.findById(id).orElse(null);
	}

	@Override
	public void unsubscribeStudent(long courseId, long studentId) {
		Course course = courseRepository.getOne(courseId);

		for (User s : course.getStudents()) {
			if (s.getId() == studentId) {
				s.getCourses().remove(course);
			}
		}

		courseRepository.save(course);
	}
    
}
