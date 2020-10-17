package com.rmiranda.schoolmanagement.web.controller;

import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.GradeService;
import com.rmiranda.schoolmanagement.service.SubjectService;
import com.rmiranda.schoolmanagement.service.UserService;

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
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @GetMapping(name = "")
    public ModelAndView show(@RequestParam(name = "sid") long subjectId, ModelAndView mv) {
        List<Grade> grades = gradeService.getGradesBySubjectId(subjectId);
        Subject subject = subjectService.getSubjectById(subjectId);
        mv.addObject("subject", subject);
        mv.addObject("grades", grades);
        mv.setViewName("grades/show");
        return mv;
    }

    @GetMapping("/{subjectId}/create")
    public ModelAndView create(@PathVariable(name = "subjectId") long subjectId, ModelAndView mv) {
        Grade grade = new Grade();
        Subject subject = subjectService.getSubjectById(subjectId);
        grade.setSubject(subject);
        mv.addObject("grade", grade);
        mv.setViewName("grades/create");
        return mv;
    }

    @PostMapping("/{subjectId}/create")
    public ModelAndView store(@PathVariable(name = "subjectId") long subjectId, @Valid Grade grade,
            BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/create");
            return mv;
        }

        gradeService.addGrade(grade);

        mv.setViewName("redirect:/grades/".concat(String.valueOf(grade.getId())).concat("/register"));

        return mv;
    }

    @GetMapping("/{gradeId}/register")
    public ModelAndView showRegister(@PathVariable(name = "gradeId") long gradeId, ModelAndView mv) {
        List<GradeDetail> scorebook = gradeService.getGradeScorebook(gradeId);
        Grade grade = gradeService.getGradeById(gradeId);
        mv.addObject("grade", grade);
        mv.addObject("scorebook", scorebook);
        mv.setViewName("grades/register");
        return mv;
    }

    @GetMapping("/{gradeId}/register/{studentId}")
    public ModelAndView showRegisterGradeToStudent(@PathVariable(name = "gradeId") long gradeId,
            @PathVariable(name = "studentId") long studentId, ModelAndView mv) {
        Grade grade = gradeService.getGradeById(gradeId);
        User student = userService.getUserById(studentId).orElse(null);
        GradeDetail score = new GradeDetail();
        
        score.setGrade(grade);
        score.setStudent(student);

        mv.addObject("score", score);
        mv.setViewName("grades/registerGradeToStudent");

        return mv;
    }

    @PostMapping("/{gradeId}/register/{studentId}")
    public ModelAndView registerGradeToStudent(@PathVariable(name = "gradeId") long gradeId,
            @PathVariable(name = "studentId") long studentId, @Valid GradeDetail score, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/registerGradeToStudent");
            return mv;
        }

        Grade grade = gradeService.getGradeById(gradeId);
        List<GradeDetail> scores = grade.getScores();
        scores.add(score);

        gradeService.updateGrade(grade);

        mv.setViewName("redirect:/grades/".concat(String.valueOf(gradeId)).concat("/register"));

        return mv;
    }

    @GetMapping("/{gradeId}/edit")
    public ModelAndView showUpdate(@PathVariable(name = "gradeId") long gradeId, ModelAndView mv) {
        Grade grade = gradeService.getGradeById(gradeId);
        mv.addObject("grade", grade);
        mv.setViewName("grades/edit");
        mv.setViewName("redirect:/grades/".concat(String.valueOf(gradeId)).concat("/register"));
        return mv;
    }

    @PostMapping("/{radeId}/edit")
    public ModelAndView update(@PathVariable(name = "gradeId") long gradeId, @Valid Grade grade, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/edit");
            return mv;
        }

        gradeService.updateGrade(grade);

        mv.setViewName("redirect:/grades?sid=".concat(String.valueOf(grade.getSubject().getId())));

        return mv;
    }

    @GetMapping("/{gradeId}/delete")
    public ModelAndView showDelete(@PathVariable(name = "gradeId") long gradeId, ModelAndView mv) {
        Grade grade = gradeService.getGradeById(gradeId);

        mv.addObject("grade", grade);
        mv.setViewName("grades/delete");

        return mv;
    }

    @PostMapping("/{gradeId}/delete")
    public ModelAndView delete(@PathVariable(name = "gradeId") long gradeId, @Valid Grade grade, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/delete");
            return mv;
        }

        gradeService.deleteGrade(grade);

        mv.setViewName("redirect:/grades?sid=".concat(String.valueOf(grade.getSubject().getId())));

        return mv;
    }

}
