package com.ms.training.application.dto.response;

import com.ms.training.application.dto.Actor;
import com.ms.training.application.dto.training.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String userName;
    String password;
    String token;
//    RoleDTO roleDTO;
    String is;
    Boolean isAdmin;
    Actor actor;
    String role;
    ProfileDTO profile;
    AccountDTO account;
    MyClassDTO aClass;
}
