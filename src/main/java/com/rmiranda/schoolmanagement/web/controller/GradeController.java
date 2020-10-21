package com.rmiranda.schoolmanagement.web.controller;

import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.GradeDetailService;
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

    @Autowired
    private GradeDetailService scoreService;

    @GetMapping(name = "")
    public ModelAndView show(@RequestParam(name = "sid") long subjectId, ModelAndView mv) {
        List<Grade> grades = gradeService.getGradesBySubjectId(subjectId);
        Subject subject = subjectService.getSubjectById(subjectId);
        mv.addObject("subject", subject);
        mv.addObject("grades", grades);
        mv.setViewName("grades/show");
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(name = "sid") long subjectId, ModelAndView mv) {
        Grade grade = new Grade();
        Subject subject = subjectService.getSubjectById(subjectId);
        grade.setSubject(subject);
        mv.addObject("grade", grade);
        mv.setViewName("grades/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView store(@RequestParam(name = "sid") long subjectId, @Valid Grade grade, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/create");
            return mv;
        }

        gradeService.addGrade(grade);

        mv.setViewName("redirect:/grades/".concat(String.valueOf(grade.getId())));

        return mv;
    }

    @GetMapping("/{gradeId}")
    public ModelAndView showRegister(@PathVariable(name = "gradeId") long gradeId, ModelAndView mv) {
        List<GradeDetail> scorebook = gradeService.getGradeScorebook(gradeId);
        Grade grade = gradeService.getGradeById(gradeId);
        mv.addObject("grade", grade);
        mv.addObject("scorebook", scorebook);
        mv.setViewName("grades/register");
        return mv;
    }

    @GetMapping("/{gradeId}/register")
    public ModelAndView showRegisterGradeToStudent(@PathVariable(name = "gradeId") long gradeId,
            @RequestParam(name = "sid") long studentId, ModelAndView mv) {
        Grade grade = gradeService.getGradeById(gradeId);
        User student = userService.getUserById(studentId).orElse(null);
        GradeDetail score = new GradeDetail();

        score.setGrade(grade);
        score.setStudent(student);

        mv.addObject("score", score);
        mv.setViewName("grades/registerGradeToStudent");

        return mv;
    }

    @PostMapping("/{gradeId}/register")
    public ModelAndView registerGradeToStudent(@PathVariable(name = "gradeId") long gradeId,
            @RequestParam(name = "sid") long studentId, @Valid GradeDetail score, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/registerGradeToStudent");
            return mv;
        }

        Grade grade = gradeService.getGradeById(gradeId);
        List<GradeDetail> scores = grade.getScores();
        scores.add(score);

        gradeService.updateGrade(grade);

        mv.setViewName("redirect:/grades/".concat(String.valueOf(gradeId)));

        return mv;
    }

    @GetMapping("/{gradeId}/edit")
    public ModelAndView showUpdate(@PathVariable(name = "gradeId") long gradeId, ModelAndView mv) {
        Grade grade = gradeService.getGradeById(gradeId);
        mv.addObject("grade", grade);
        mv.setViewName("grades/edit");
        return mv;
    }

    @PostMapping("/{gradeId}/edit")
    public ModelAndView update(@PathVariable(name = "gradeId") long gradeId, @Valid Grade grade, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/edit");
            return mv;
        }

        Grade modified = gradeService.updateGrade(grade);

        mv.setViewName("redirect:/grades?sid=".concat(String.valueOf(modified.getSubject().getId())));

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

    @GetMapping("/{gradeId}/score/{scoreId}/edit")
    public ModelAndView editStudentScore(@PathVariable(name = "gradeId") long gradeId,
            @PathVariable(name = "scoreId") long scoreId, @Valid GradeDetail grade, ModelAndView mv) {

        GradeDetail score = scoreService.getScoreById(scoreId);

        mv.addObject("score", score);
        mv.setViewName("grades/editScore");

        return mv;
    }

    @PostMapping("/{gradeId}/score/{scoreId}/edit")
    public ModelAndView updateStudentScore(@PathVariable(name = "gradeId") long gradeId,
            @PathVariable(name = "scoreId") long scoreId, @Valid GradeDetail score, BindingResult result,
            ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("grades/editScore");
            return mv;
        }

        GradeDetail updatedScore = scoreService.updateScore(score);

        mv.setViewName("redirect:/grades/".concat(String.valueOf(updatedScore.getGrade().getId())));

        return mv;
    }

}
