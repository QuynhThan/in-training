package com.ms.training.application.service;

import com.ms.training.application.dto.AccountDTO;
import com.ms.training.application.dto.response.UserDTO;

public interface AccountService {
    AccountDTO addAccount(AccountDTO dto);
    UserDTO updateAccount(UserDTO dto);
    UserDTO login(UserDTO dto);
}
