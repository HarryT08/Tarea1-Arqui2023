package com.rmiranda.schoolmanagement.model.repository;

import com.rmiranda.schoolmanagement.model.entity.Classroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    
}
