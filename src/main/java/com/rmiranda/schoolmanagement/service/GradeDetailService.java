package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.entity.GradeDetail;

public interface GradeDetailService {

    public GradeDetail getScoreById(long id);

    public GradeDetail updateScore(GradeDetail scoreForm);
    
}
