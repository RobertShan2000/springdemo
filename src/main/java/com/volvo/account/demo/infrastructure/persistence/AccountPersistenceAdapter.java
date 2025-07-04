package com.volvo.account.demo.infrastructure.persistence;

import com.volvo.account.demo.domain.entity.Account;
import com.volvo.account.demo.domain.repository.AccountRepository;
import com.volvo.account.demo.infrastructure.persistence.entity.AccountJpaEntity;
import com.volvo.account.demo.infrastructure.persistence.mapper.AccountMapper;
import com.volvo.account.demo.infrastructure.persistence.repository.AccountJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountPersistenceAdapter implements AccountRepository {

    private final AccountJpaRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountPersistenceAdapter(AccountJpaRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        AccountJpaEntity jpaEntity = accountMapper.toJpaEntity(account);
        AccountJpaEntity savedEntity = accountRepository.save(jpaEntity);
        return accountMapper.toDomainEntity(savedEntity);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        Optional<AccountJpaEntity> jpaEntityOptional = accountRepository.findByEmail(email);
        return jpaEntityOptional.map(accountMapper::toDomainEntity);
    }

    @Override
    public List<Account> findByLastUpdatedAfter(LocalDateTime dateTime, int page, int size) {
        // 注意：这里简化了分页实现，实际应使用Spring Data的Pageable
        List<AccountJpaEntity> jpaEntities = accountRepository.findByLastUpdatedAfter(dateTime);

        // 分页处理
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, jpaEntities.size());
        if (startIndex >= jpaEntities.size()) {
            return List.of();
        }

        List<AccountJpaEntity> pagedEntities = jpaEntities.subList(startIndex, endIndex);

        return pagedEntities.stream()
                .map(accountMapper::toDomainEntity)
                .collect(Collectors.toList());
    }
}    
