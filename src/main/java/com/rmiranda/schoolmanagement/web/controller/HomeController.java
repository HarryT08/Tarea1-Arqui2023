package com.rmiranda.schoolmanagement.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
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
    public ModelAndView home(ModelAndView mv, HttpServletRequest request, Principal principal) {

        if (request.isUserInRole("ADMIN")) {
            int students = dashboardService.getTotalStudents();
            int teachers = dashboardService.getTotalTeachers();
            int classrooms = dashboardService.getTotalClassrooms();
            mv.addObject("students", students);
            mv.addObject("teachers", teachers);
            mv.addObject("classrooms", classrooms);
        }

        if (request.isUserInRole("TEACHER")) {
            List<Course> courses = dashboardService.getCoursesByTeacher(principal.getName());
            List<Subject> subjects = dashboardService.getSubjectsByTeacher(principal.getName());
            mv.addObject("teacherSubjects", subjects);
            mv.addObject("teacherCourses", courses);
        }


        mv.setViewName("home");
        return mv;
    }
}