package com.volvo.account.demo.domain.service;

import com.volvo.account.demo.domain.entity.Card;

import java.time.LocalDateTime;
import java.util.List;

public interface CardService {
    Card createCard(String cardId, String contractId);
    Card assignCard(String cardId, String accountEmail);
    Card activateCard(String cardId);
    Card deactivateCard(String cardId);
    List<Card> getCardsByLastUpdated(LocalDateTime dateTime, int page, int size);
}
