package com.rmiranda.schoolmanagement.web.controller;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;
import com.rmiranda.schoolmanagement.service.ScheduleService;
import com.rmiranda.schoolmanagement.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(name = "sid") long subjectId, SubjectSchedule schedule, ModelAndView mv) {
        Subject subject = subjectService.getSubjectById(subjectId);
        schedule.setSubject(subject);
        mv.addObject("schedule", schedule);
        mv.setViewName("schedules/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "sid") long subjectId, @Valid SubjectSchedule schedule, BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("schedules/create");
            return mv;
        }

        scheduleService.save(schedule);

        mv.setViewName("redirect:/subjects/".concat(String.valueOf(subjectId)));

        return mv;
    }

    @GetMapping("/{scheduleId}/delete")
    public ModelAndView showDelete(@PathVariable(name = "scheduleId") long scheduleId, ModelAndView mv) {
        SubjectSchedule schedule = scheduleService.getScheduleById(scheduleId).orElse(null);
        mv.addObject("schedule", schedule);
        mv.setViewName("schedules/delete");
        return mv;
    }

    @PostMapping("/{scheduleId}/delete")
    public ModelAndView delete(@PathVariable(name = "scheduleId") long scheduleId, SubjectSchedule schedule, ModelAndView mv) {
        schedule = scheduleService.getScheduleById(scheduleId).orElse(null);
        String subjectId = String.valueOf(schedule.getSubject().getId());
        scheduleService.deleteSchedule(schedule);
        mv.setViewName("redirect:/subjects/".concat(subjectId));
        return mv;
    }

}