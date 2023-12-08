package com.ms.training.application.service.Impl;

import com.ms.training.application.dto.request.SubmitSubjectRequest;
import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.application.service.InTrainingService;
import com.ms.training.domain.service.InTrainingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InTrainingServiceImpl implements InTrainingService {
    @Autowired
    InTrainingData inTrainingData;
    @Override
    public ClassCreditDTO submitRegistration(SubmitSubjectRequest request) {
        return inTrainingData.submitRegistration(request);
    }
}
