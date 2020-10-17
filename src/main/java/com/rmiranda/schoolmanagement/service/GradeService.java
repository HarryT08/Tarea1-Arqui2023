package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Grade;
import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.entity.Subject;

public interface GradeService {

    public void addGrade(Grade grade);

    public Grade getGradeById(long id);

    public List<Grade> getGradesBySubject(Subject subject);

    public List<Grade> getGradesBySubjectId(long subjectId);

    public void updateGrade(Grade grade);

    public void deleteGradeById(long id);

    public void deleteGrade(Grade grade);

    public List<GradeDetail> getGradeScorebook(long gradeId);
    
}
