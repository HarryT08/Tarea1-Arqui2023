package com.rmiranda.schoolmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.entity.Subject;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.GradeDetailRepository;
import com.rmiranda.schoolmanagement.model.repository.GradeRepository;
import com.rmiranda.schoolmanagement.model.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeDetailRepository scoreRepository;

    @Override
    public void addGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public Grade getGradeById(long id) {
        return gradeRepository.getOne(id);
    }

    @Override
    public List<Grade> getGradesBySubject(Subject subject) {
        return gradeRepository.findBySubject(subject);
    }

    @Override
    public List<Grade> getGradesBySubjectId(long subjectId) {
        Subject subject = subjectRepository.getOne(subjectId);
        return gradeRepository.findBySubject(subject);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public void deleteGradeById(long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public void deleteGrade(Grade grade) {
        gradeRepository.delete(grade);
    }

    @Override
    public List<GradeDetail> getGradeScorebook(long gradeId) {
        List<GradeDetail> scorebook = new ArrayList<>();
        Grade grade = gradeRepository.getOne(gradeId);
        List<User> students = grade.getSubject().getCourse().getStudents();

        for (User student : students) {
            GradeDetail score = scoreRepository.findByGradeAndStudent(grade, student).orElse(null);
            if (score == null) {
                GradeDetail nscore = new GradeDetail();
                nscore.setStudent(student);
                nscore.setGrade(grade);
                scorebook.add(nscore);
                continue;
            } 
            scorebook.add(score);
        }

        return scorebook;
    }
    
}
