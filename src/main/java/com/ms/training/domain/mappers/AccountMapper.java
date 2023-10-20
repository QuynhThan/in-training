package com.ms.training.domain.mappers;

import com.ms.training.application.dto.AccountDTO;
import com.ms.training.domain.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    public static AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toAccountDto(Account account);
    List<AccountDTO> toAccountDtoList(List<Account> accounts);
    List<Account> toAccountList(List<AccountDTO> accountDTOS);
    Account toAccount(AccountDTO dto);

}
