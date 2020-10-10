package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<SubjectSchedule, Long> {
    
    public List<SubjectSchedule> findByDayOfWeekAndSubjectInOrderByStartTimeAsc(int dayOfWeek, List<Subject> subject);
}
