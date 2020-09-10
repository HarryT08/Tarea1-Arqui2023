package com.rmiranda.schoolmanagement.web.controller;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Classroom;
import com.rmiranda.schoolmanagement.service.ClassroomService;

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
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Classroom> classrooms = classroomService.getAllClassrooms(pageable);

        mv.addObject("classrooms", classrooms);

        mv.setViewName("classrooms/index");
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(Classroom classroom, ModelAndView mv) {
        mv.addObject("classroom", classroom);
        mv.setViewName("classrooms/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@Valid Classroom classroom, BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("classrooms/create");
            return mv;
        }

        classroomService.addClassroom(classroom);

        mv.setViewName("redirect:/classrooms");

        return mv;
    }

    @GetMapping("/{classroomId}/edit")
    public ModelAndView edit(@PathVariable(name = "classroomId") long classroomId, Classroom classroom,
            ModelAndView mv) {

        classroom = classroomService.getClassroomById(classroomId);

        if (classroom == null) {
            mv.setViewName("redirect:/classrooms");
            return mv;
        }

        mv.addObject("classroom", classroom);

        mv.setViewName("classrooms/edit");

        return mv;
    }

    @PostMapping("/{classroomId}/edit")
    public ModelAndView update(@PathVariable(name = "classroomId") long classroomId, @Valid Classroom classroom,
            BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("classrooms/edit");
            return mv;
        }

        classroomService.updateClassroom(classroom);

        mv.setViewName("redirect:/classrooms");

        return mv;
    }

}
