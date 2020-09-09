package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.entity.Cycle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CycleService {

    public void addCycle(Cycle cycle);

    public Page<Cycle> getAllCycles(Pageable page);
    
}
