package com.rmiranda.schoolmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.repository.CycleRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CycleRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CycleRepository cycleRepository;

    @Test
    public void testAddCycle() {
        Cycle cycle = new Cycle();
        cycle.setName("ciclo de prueba");

        cycleRepository.save(cycle);

        Cycle dbCycle = em.find(Cycle.class, cycle.getId());

        assertTrue(dbCycle.getName().equals(cycle.getName()));

    }
    
}
