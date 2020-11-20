package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    public List<Subject> findByCourse(Course course);

    public List<Subject> findByTeacher(User teacher);
    
}
