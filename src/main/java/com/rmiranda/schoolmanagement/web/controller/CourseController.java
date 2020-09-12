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

    @GetMapping("")
    public ModelAndView index(Cycle cycle, ModelAndView mv) {

        List<Cycle> cycles = cycleService.getActiveCycles();
        mv.addObject("cycles", cycles);

        if (cycle.getId() > 0) {
            List<Course> courses = courseService.getAllCoursesByCycle(cycle);
            mv.addObject("courses", courses);
        }

        mv.setViewName("courses/index");

        return mv;
    }

    @GetMapping("/create")
    public ModelAndView add(@RequestParam(name = "cid") long cycleId, Course course, ModelAndView mv) {

        Cycle cycle = cycleService.getcycleById(cycleId);
        course.setCycle(cycle);

        List<Classroom> classrooms = classroomService.getAllClassrooms();
        List<User> managers = roleService.getRoleByName("ROLE_TEACHER").getUsers();

        mv.addObject("classrooms", classrooms);
        mv.addObject("managers", managers);
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

}
