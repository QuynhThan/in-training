package com.ms.training.domain.service;

import com.ms.training.application.dto.request.SubmitSubjectRequest;
import com.ms.training.application.dto.training.ClassCreditDTO;

public interface InTrainingData {
    ClassCreditDTO submitRegistration(SubmitSubjectRequest request);
}
