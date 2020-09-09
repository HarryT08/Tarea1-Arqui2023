package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.repository.CycleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CycleServiceImpl implements CycleService {

    @Autowired
    private CycleRepository cycleRepository;

    @Override
    public void addCycle(Cycle cycle) {
        cycleRepository.save(cycle);
    }

    @Override
    public Page<Cycle> getAllCycles(Pageable page) {
        return cycleRepository.findAll(page);
    }
    
}