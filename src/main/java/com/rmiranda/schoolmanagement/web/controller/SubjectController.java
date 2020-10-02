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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ModelAttribute("classrooms")
    public List<Classroom> getClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @ModelAttribute("teachers")
    public List<User> teachers() {
        return roleService.getRoleByName("ROLE_TEACHER").getUsers();
    }

    @GetMapping("/create")
    public ModelAndView index(@RequestParam(name = "cid") long courseId, Subject subject,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "teachers") List<User> teachers, ModelAndView mv) {

        Course course = courseService.getCourseById(courseId);
        subject.setCourse(course);

        mv.addObject("subject", subject);
        mv.setViewName("subjects/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "cid") long courseId, @Valid Subject subject, BindingResult result,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "teachers") List<User> teachers, ModelAndView mv) {

        Course course = courseService.getCourseById(courseId);
        subject.setCourse(course);

        if (result.hasErrors()) {
            mv.setViewName("subjects/create");
            return mv;
        }

        subjectService.addSubject(subject);

        mv.setViewName("redirect:/subjects/".concat(String.valueOf(subject.getId())));

        return mv;
    }

    @GetMapping("/{subjectId}/edit")
    public ModelAndView edit(@PathVariable(name = "subjectId") long subjectId, Subject subject,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "teachers") List<User> teachers, ModelAndView mv) {
        subject = subjectService.getSubjectById(subjectId);
        mv.addObject("subject", subject);
        mv.setViewName("subjects/edit");
        return mv;
    }

    @PostMapping("/{subjectId}/edit")
    public ModelAndView update(@PathVariable(name = "subjectId") long subjectId, @Valid Subject subject,
            BindingResult result, @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "teachers") List<User> teachers, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("subjects/edit");
            return mv;
        }

        subjectService.updateSubject(subject);

        mv.setViewName("redirect:/courses/".concat(String.valueOf(subject.getCourse().getId())));

        return mv;
    }

    @GetMapping("/{subjectId}/delete")
    public ModelAndView showDelete(@PathVariable(name = "subjectId") long subjectId, Subject subject, ModelAndView mv) {
        subject = subjectService.getSubjectById(subjectId);
        mv.addObject("subject", subject);
        mv.setViewName("subjects/delete");
        return mv;
    }

    @PostMapping("/{subjectId}/delete")
    public ModelAndView delete(@PathVariable(name = "subjectId") long subjectId, Subject subject, ModelAndView mv) {
        
        String courseId = String.valueOf(subject.getCourse().getId());

        if (subjectId > 0) {
            subjectService.deleteSubjectById(subjectId);
        }

        mv.setViewName("redirect:/courses/".concat(courseId));

        return mv;
    }

    @GetMapping("/{subjectId}")
    public ModelAndView show(@PathVariable(name = "subjectId") long subjectId, ModelAndView mv) {
        Subject subject = subjectService.getSubjectById(subjectId);
        mv.addObject("subject", subject);
        mv.setViewName("subjects/show");
        return mv;
    }

}
