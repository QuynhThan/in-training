package com.ms.training.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    private Integer orderId;
    private String status;
    private String staffId;
}
