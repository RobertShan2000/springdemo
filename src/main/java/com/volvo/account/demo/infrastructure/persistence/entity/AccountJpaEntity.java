package com.volvo.account.demo.infrastructure.persistence.entity;

import com.volvo.account.demo.domain.valueobject.AccountStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountJpaEntity {

    @Id
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardJpaEntity> cards = new ArrayList<>();

    // JPA需要的无参构造函数
    protected AccountJpaEntity() {}

    public AccountJpaEntity(String email, AccountStatus status, LocalDateTime createdAt, LocalDateTime lastUpdated) {
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    // getters and setters
    public String getEmail() {
        return email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<CardJpaEntity> getCards() {
        return cards;
    }

    public void addCard(CardJpaEntity card) {

    }

    public void setCards(List<CardJpaEntity> cardJpaEntities) {
    }
}
