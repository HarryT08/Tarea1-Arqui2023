package com.rmiranda.schoolmanagement.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.SubjectSchedule;
import com.rmiranda.schoolmanagement.service.CourseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ModelAndView showCalendar(@RequestParam(name = "cid") long courseId, ModelAndView mv) {
        HashMap<String, List<SubjectSchedule>> schedule = scheduleService.getScheduleByCourseId(courseId);
        Course course = courseService.getCourseById(courseId);
        mv.addObject("course", course);
        mv.addObject("schedule", schedule);

        mv.setViewName("schedules/show");

        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(name = "sid") long subjectId, SubjectSchedule schedule, ModelAndView mv) {
        Subject subject = subjectService.getSubjectById(subjectId);
        schedule.setSubject(subject);
        mv.addObject("schedule", schedule);
        mv.setViewName("schedules/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "sid") long subjectId, @Valid SubjectSchedule schedule,
            BindingResult result, ModelAndView mv, RedirectAttributes attr) {

        if (result.hasErrors()) {
            mv.setViewName("schedules/create");
            return mv;
        }

        scheduleService.save(schedule);

        attr.addAttribute("screated", "1");
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
    public ModelAndView delete(@PathVariable(name = "scheduleId") long scheduleId, SubjectSchedule schedule,
            ModelAndView mv, RedirectAttributes attr) {
        schedule = scheduleService.getScheduleById(scheduleId).orElse(null);
        String subjectId = String.valueOf(schedule.getSubject().getId());
        scheduleService.deleteSchedule(schedule);
        attr.addAttribute("sdeleted", "1");
        mv.setViewName("redirect:/subjects/".concat(subjectId));
        return mv;
    }

}