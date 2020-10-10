package com.rmiranda.schoolmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;

public interface ScheduleService {

    public void save(SubjectSchedule schedule);

    public Optional<SubjectSchedule> getScheduleById(long id);

    public void deleteSchedule(SubjectSchedule schedule);

    public HashMap<String, List<SubjectSchedule>> getScheduleByCourseId(long courseId);
    
}
