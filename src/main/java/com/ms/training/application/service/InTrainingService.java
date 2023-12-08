package com.ms.training.application.service;

import com.ms.training.application.dto.request.SubmitSubjectRequest;
import com.ms.training.application.dto.training.ClassCreditDTO;

public interface InTrainingService {
    ClassCreditDTO submitRegistration(SubmitSubjectRequest request);
}
