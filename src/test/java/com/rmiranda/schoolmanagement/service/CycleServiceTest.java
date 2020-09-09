package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.repository.CycleRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    public void testGetAllCycles() {
        Pageable page = PageRequest.of(0, 10);
        Page<Cycle> cyclesPage = cycleService.getAllCycles(page);

        assertTrue(cyclesPage.getNumberOfElements() > 0);
    }

    @Test
    public void testUpdateCycle() {
        Cycle cycle = em.find(Cycle.class, Long.valueOf(1));

        cycle.setName("updated cycle");

        cycleService.updateCycle(cycle);

        Cycle updatedCycle = em.find(Cycle.class, Long.valueOf(1));

        assertTrue(cycle.getName().equals(updatedCycle.getName()));
    }
    
}
