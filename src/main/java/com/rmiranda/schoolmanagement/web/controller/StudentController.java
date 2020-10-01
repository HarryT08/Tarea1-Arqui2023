package com.rmiranda.schoolmanagement.web.controller;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Course;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.CourseService;
import com.rmiranda.schoolmanagement.service.RoleService;
import com.rmiranda.schoolmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @ModelAttribute("students")
    public List<User> students() {
        return roleService.getRoleByName("ROLE_STUDENT").getUsers();
    }

    @GetMapping("/subscribe")
    public ModelAndView showSubscribe(@RequestParam(name = "cid") long courseId,
            @ModelAttribute(name = "students") List<User> students, ModelAndView mv) {
        Course course = courseService.getCourseById(courseId);
        mv.addObject("course", course);
        mv.setViewName("students/subscribe");
        return mv;
    }

    @PostMapping("subscribe")
    public ModelAndView subscribe(@RequestParam(name = "cid") long courseId,
            @RequestParam(name = "student") long studentId, ModelAndView mv) {

        Course course = courseService.getCourseById(courseId);

        if (studentId > 0) {
            List<User> students = course.getStudents();
            User student = userService.getUserById(studentId).orElse(null);

            if (student != null) {
                students.add(student);
                course.setStudents(students);
                courseService.updateCourse(course);
            }

        }

        mv.setViewName("redirect:/courses/".concat(String.valueOf(courseId)));

        return mv;
    }

    @PostMapping("/unsubscribe")
    public ModelAndView unsubscribe(@RequestParam(name = "cid") long courseId,
            @RequestParam(name = "sid") long studentId, ModelAndView mv) {
        Course course = courseService.getCourseById(courseId);
        User student = userService.getUserById(studentId).orElse(null);

        if (course.getStudents().indexOf(student) >= 0) {
            course.getStudents().remove(student);
        }

        courseService.updateCourse(course);

        mv.setViewName("redirect:/courses/".concat(String.valueOf(courseId)));

        return mv;
    }

    @GetMapping("/unsubscribe")
    public ModelAndView showUnsubscribe(@RequestParam(name = "cid") long courseId,
            @RequestParam(name = "sid") long studentId, ModelAndView mv) {
        Course course = courseService.getCourseById(courseId);
        User student = userService.getUserById(studentId).orElse(null);
        mv.addObject("course", course);
        mv.addObject("student", student);
        mv.setViewName("students/unsubscribe");
        return mv;
    }

}
