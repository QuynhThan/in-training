package com.ms.training.application.service;

import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.dto.training.AccountDTO;

public interface AccountService {
    com.ms.training.application.dto.training.AccountDTO addAccount(AccountDTO dto);
    UserDTO updateAccount(UserDTO dto);
    UserDTO login(UserDTO dto);
}
