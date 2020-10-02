package com.rmiranda.schoolmanagement.service;

import java.util.Optional;

import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;
import com.rmiranda.schoolmanagement.model.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(SubjectSchedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public Optional<SubjectSchedule> getScheduleById(long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public void deleteSchedule(SubjectSchedule schedule) {
        scheduleRepository.delete(schedule);
    }
    
}
