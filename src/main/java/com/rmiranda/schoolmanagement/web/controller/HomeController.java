package com.rmiranda.schoolmanagement.web.controller;

import com.rmiranda.schoolmanagement.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping(value = {"home", ""})
    public ModelAndView home(ModelAndView mv){
        int students = dashboardService.getTotalStudents();
        int teachers = dashboardService.getTotalTeachers();
        int classrooms = dashboardService.getTotalClassrooms();

        mv.addObject("students", students);
        mv.addObject("teachers", teachers);
        mv.addObject("classrooms", classrooms);

        mv.setViewName("home");
        return mv;
    }
}