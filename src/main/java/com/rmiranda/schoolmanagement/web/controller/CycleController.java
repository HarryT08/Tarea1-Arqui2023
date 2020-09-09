package com.rmiranda.schoolmanagement.web.controller;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.service.CycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cycles")
public class CycleController {

    @Autowired
    private CycleService cycleService;

    @GetMapping("/create")
    public ModelAndView create(Cycle cycle, ModelAndView mv) {
        mv.addObject("cycle", cycle);
        mv.setViewName("cycles/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@Valid Cycle cycle, BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("cycles/create");
            return mv;
        }

        cycleService.addCycle(cycle);

        mv.setViewName("redirect:/cycles");

        return mv;
    }

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cycle> cycles = cycleService.getAllCycles(pageable);

        mv.addObject("cycles", cycles);

        mv.setViewName("cycles/index");
        return mv;
    }

    @GetMapping("/{cycleId}/edit")
    public ModelAndView edit(@PathVariable(name = "cycleId") long cycleId, Cycle cycle, ModelAndView mv) {
        cycle = cycleService.getcycleById(cycleId);

        mv.addObject("cycle", cycle);

        mv.setViewName("cycles/edit");

        return mv;
    }

    @PostMapping("/{cycleId}/edit")
    public ModelAndView update(@Valid Cycle cycle, BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("cycles/edit");
            return mv;
        }

        cycleService.updateCycle(cycle);

        mv.setViewName("redirect:/cycles");

        return mv;
    }

    
}
