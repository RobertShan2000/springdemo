package com.volvo.account.demo.domain.entity;

import com.volvo.account.demo.domain.valueobject.AccountStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String email;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private List<Card> cards;

    public Account(String email) {
        this.email = email;
        this.status = AccountStatus.INACTIVE;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.cards = new ArrayList<>();
    }

    public void activate() {
        this.status = AccountStatus.ACTIVE;
        this.lastUpdated = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = AccountStatus.INACTIVE;
        this.lastUpdated = LocalDateTime.now();
    }

    public void assignCard(Card card) {
        cards.add(card);
        card.assignToAccount(this.email);
        this.lastUpdated = LocalDateTime.now();
    }
}