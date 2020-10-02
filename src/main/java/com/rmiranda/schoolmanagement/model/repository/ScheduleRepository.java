package com.rmiranda.schoolmanagement.model.repository;

import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<SubjectSchedule, Long> {
    
}
