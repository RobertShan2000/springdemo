package com.volvo.account.demo.infrastructure.persistence.repository;

import com.volvo.account.demo.infrastructure.persistence.entity.CardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CardJpaRepository extends JpaRepository<CardJpaEntity, String> {

    Optional<CardJpaEntity> findByCardId(String cardId);

    @Query("SELECT c FROM CardJpaEntity c WHERE c.lastUpdated > :dateTime ORDER BY c.lastUpdated ASC")
    List<CardJpaEntity> findByLastUpdatedAfter(@Param("dateTime") LocalDateTime dateTime);
}
