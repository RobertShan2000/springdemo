package com.volvo.account.demo.domain.entity;

import com.volvo.account.demo.domain.valueobject.CardStatus;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private String cardId;
    private String emaid;
    private String contractId;
    private String accountEmail;
    private CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    public Card(String cardId, String emaid, String contractId) {
        this.cardId = cardId;
        this.emaid = emaid;
        this.contractId = contractId;
        this.status = CardStatus.INACTIVE;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    public void assignToAccount(String accountEmail) {
        this.accountEmail = accountEmail;
        this.status = CardStatus.ASSIGNED;
        this.lastUpdated = LocalDateTime.now();
    }

    public void activate() {
        this.status = CardStatus.ACTIVE;
        this.lastUpdated = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = CardStatus.INACTIVE;
        this.lastUpdated = LocalDateTime.now();
    }

}
