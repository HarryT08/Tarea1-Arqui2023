package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.entity.GradeDetail;
import com.rmiranda.schoolmanagement.model.repository.GradeDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeDetailServiceImpl implements GradeDetailService {
    
    @Autowired
    private GradeDetailRepository gradeDetailReposutory;

    @Override
    public GradeDetail getScoreById(long id) {
        return gradeDetailReposutory.getOne(id);
    }

    @Override
    public GradeDetail updateScore(GradeDetail scoreForm) {
        GradeDetail score = gradeDetailReposutory.getOne(scoreForm.getId());
        score.setScore(scoreForm.getScore());

        gradeDetailReposutory.save(score);

        return score;
    }
    
}
