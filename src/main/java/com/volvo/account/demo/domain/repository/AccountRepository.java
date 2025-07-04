package com.volvo.account.demo.domain.repository;

import com.volvo.account.demo.domain.entity.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByEmail(String email);
    List<Account> findByLastUpdatedAfter(LocalDateTime dateTime, int page, int size);
}
