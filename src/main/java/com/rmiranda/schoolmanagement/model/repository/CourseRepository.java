package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public List<Course> findByCycle(Cycle cycle);

}
