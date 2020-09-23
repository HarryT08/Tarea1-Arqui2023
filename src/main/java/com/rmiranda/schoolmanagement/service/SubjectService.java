package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Subject;

public interface SubjectService {

    public void addSubject(Subject subject);

    public void updateSubject(Subject subject);

    public List<Subject> getAllSubjectsByCourseId(long courseId);

    public Subject getSubjectById(long id);

    public void deleteSubjectById(long id);
}
