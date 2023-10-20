package com.ms.training.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actor {
    private String id;
    private String name;
    private String gender;
    private LocalDateTime dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String status;
}
