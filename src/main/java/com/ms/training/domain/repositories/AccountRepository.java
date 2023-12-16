package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findFirstByUsernameAndPassword(String username, String password);
    Account findByUsername(String username);
}
