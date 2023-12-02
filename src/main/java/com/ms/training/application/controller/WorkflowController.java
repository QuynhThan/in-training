package com.ms.training.application.controller;

import com.ms.training.application.constant.ApiConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ApiConstant.V1 + ApiConstant.USER)
public class WorkflowController {
}
