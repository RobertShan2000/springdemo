package com.volvo.account.demo.domain.service;

import com.volvo.account.demo.domain.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {
    Account createAccount(String email);
    Account activateAccount(String email);
    Account deactivateAccount(String email);
    List<Account> getAccountsByLastUpdated(LocalDateTime dateTime, int page, int size);
}
