package com.volvo.account.demo.domain.repository;

import com.volvo.account.demo.domain.entity.Card;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    Optional<Card> findById(String cardId);
    List<Card> findByLastUpdatedAfter(LocalDateTime dateTime, int page, int size);
}
