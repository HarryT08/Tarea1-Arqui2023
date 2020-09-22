package com.rmiranda.schoolmanagement.web.controller;

import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.ClassroomService;
import com.rmiranda.schoolmanagement.service.CourseService;
import com.rmiranda.schoolmanagement.service.RoleService;
import com.rmiranda.schoolmanagement.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/create")
    public ModelAndView index(@RequestParam(name = "cid") long courseId, Subject subject, ModelAndView mv) {

        Course course = courseService.getCourseById(courseId);
        subject.setCourse(course);

        List<User> teachers = roleService.getRoleByName("ROLE_TEACHER").getUsers();
        List<Classroom> classrooms = classroomService.getAllClassrooms();

        mv.addObject("classrooms", classrooms);
        mv.addObject("teachers", teachers);
        mv.addObject("subject", subject);
        mv.setViewName("subjects/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "cid") long courseId, @Valid Subject subject, BindingResult result,
            ModelAndView mv) {
            
        Course course = courseService.getCourseById(courseId);
        subject.setCourse(course);

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            mv.setViewName("subjects/create");
            return mv;
        }

        subjectService.addSubject(subject);

        mv.setViewName("redirect:/courses?id=".concat(String.valueOf(subject.getCourse().getCycle().getId())));

        return mv;
    }

}
