package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Cycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CycleServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CycleService cycleService;

    @Test
    public void testAddCycle() {
        Cycle cycle = new Cycle();
        cycle.setName("ciclo de servicio");

        cycleService.addCycle(cycle);

        Cycle dbCycle = em.find(Cycle.class, cycle.getId());

        assertEquals(dbCycle.getName(), cycle.getName());
    }
    
}
