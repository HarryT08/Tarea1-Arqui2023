package com.rmiranda.schoolmanagement.model.repository;

import java.util.Optional;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeDetailRepository extends JpaRepository<GradeDetail, Long> {

    public Optional<GradeDetail> findByGradeAndStudent(Grade grade, User student);
    
}