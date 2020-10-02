package com.rmiranda.schoolmanagement.service;

import java.util.Optional;

import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;

public interface ScheduleService {

    public void save(SubjectSchedule schedule);

    public Optional<SubjectSchedule> getScheduleById(long id);

    public void deleteSchedule(SubjectSchedule schedule);
    
}
