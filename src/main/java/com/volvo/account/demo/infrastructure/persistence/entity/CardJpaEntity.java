package com.volvo.account.demo.infrastructure.persistence.entity;

import com.volvo.account.demo.domain.valueobject.CardStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
public class CardJpaEntity {

    @Id
    private String cardId;

    private String emaid;
    private String contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_email")
    private AccountJpaEntity account;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    // JPA需要的无参构造函数
    protected CardJpaEntity() {}

    public CardJpaEntity(String cardId, String emaid, String contractId, CardStatus status, LocalDateTime createdAt, LocalDateTime lastUpdated) {
        this.cardId = cardId;
        this.emaid = emaid;
        this.contractId = contractId;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    // getters and setters
    public String getCardId() {
        return cardId;
    }

    public String getEmaid() {
        return emaid;
    }

    public void setEmaid(String emaid) {
        this.emaid = emaid;
    }

    public String getContractId() {
        return contractId;
    }

    public AccountJpaEntity getAccount() {
        return account;
    }

    public void setAccount(AccountJpaEntity account) {
        this.account = account;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
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
}
