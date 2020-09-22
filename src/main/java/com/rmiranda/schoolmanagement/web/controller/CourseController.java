package com.rmiranda.schoolmanagement.web.controller;

import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.Cycle;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.ClassroomService;
import com.rmiranda.schoolmanagement.service.CourseService;
import com.rmiranda.schoolmanagement.service.CycleService;
import com.rmiranda.schoolmanagement.service.RoleService;

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
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CycleService cycleService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("cycles")
    public List<Cycle> getActiveCycles() {
        return cycleService.getActiveCycles();
    }

    @ModelAttribute("classrooms")
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @ModelAttribute("managers")
    public List<User> getManagers() {
        return roleService.getRoleByName("ROLE_TEACHER").getUsers();
    }

    @GetMapping("")
    public ModelAndView index(Cycle cycle, ModelAndView mv, @ModelAttribute(name = "cycles") List<Cycle> cycles) {

        if (cycle.getId() > 0) {
            List<Course> courses = courseService.getAllCoursesByCycle(cycle);
            Cycle selectedCycle = cycleService.getcycleById(cycle.getId());
            mv.addObject("selectedCycle", selectedCycle);
            mv.addObject("courses", courses);
        }

        mv.setViewName("courses/index");

        return mv;
    }

    @GetMapping("/{courseId}")
    public ModelAndView show(@PathVariable(name = "courseId") long courseId, Course course, ModelAndView mv) {
        course = courseService.getCourseById(courseId);
        mv.addObject("course", course);
        mv.setViewName("courses/show");
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView add(@RequestParam(name = "cid") long cycleId, Course course,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "managers") List<User> managers, ModelAndView mv) {

        Cycle cycle = cycleService.getcycleById(cycleId);
        course.setCycle(cycle);

        mv.addObject("course", course);
        mv.setViewName("courses/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "cid") String cycleId, @Valid Course course, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            course.setCycle(cycleService.getcycleById(Long.valueOf(cycleId)));
            mv.setViewName("courses/create");
            return mv;
        }

        courseService.addCourse(course);

        mv.setViewName("redirect:/courses?id=".concat(cycleId));

        return mv;
    }

    @GetMapping("/{courseId}/edit")
    public ModelAndView edit(@PathVariable(name = "courseId") long courseId, Course course,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms,
            @ModelAttribute(name = "managers") List<User> managers, ModelAndView mv) {

        course = courseService.getCourseById(courseId);

        mv.addObject("course", course);
        mv.setViewName("courses/edit");

        return mv;
    }

    @PostMapping("/{courseId}/edit")
    public ModelAndView update(@PathVariable(name = "courseId") long courseId, @Valid Course course,
            BindingResult result, @ModelAttribute(name = "managers") List<User> managers,
            @ModelAttribute(name = "classrooms") List<Classroom> classrooms, ModelAndView mv) {
        
        if (result.hasErrors()) {
            mv.setViewName("courses/edit");
            return mv;
        }

        courseService.updateCourse(course);

        mv.setViewName("redirect:/courses?id=".concat(String.valueOf(course.getCycle().getId())));

        return mv;
    }

}