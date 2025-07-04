package com.volvo.account.demo.infrastructure.persistence.repository;

import com.volvo.account.demo.infrastructure.persistence.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, String> {

    Optional<AccountJpaEntity> findByEmail(String email);

    @Query("SELECT a FROM AccountJpaEntity a WHERE a.lastUpdated > :dateTime ORDER BY a.lastUpdated ASC")
    List<AccountJpaEntity> findByLastUpdatedAfter(@Param("dateTime") LocalDateTime dateTime);
}
