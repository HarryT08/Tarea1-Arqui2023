package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    public List<Grade> findBySubject(Subject subject);
    
}
