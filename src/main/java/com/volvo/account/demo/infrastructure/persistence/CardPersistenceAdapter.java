package com.volvo.account.demo.infrastructure.persistence;

import com.volvo.account.demo.domain.entity.Card;
import com.volvo.account.demo.domain.repository.CardRepository;
import com.volvo.account.demo.infrastructure.persistence.entity.CardJpaEntity;
import com.volvo.account.demo.infrastructure.persistence.mapper.CardMapper;
import com.volvo.account.demo.infrastructure.persistence.repository.CardJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CardPersistenceAdapter implements CardRepository {

    private final CardJpaRepository cardRepository;
    private final CardMapper cardMapper;

    public CardPersistenceAdapter(CardJpaRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public Card save(Card card) {
        CardJpaEntity jpaEntity = cardMapper.toJpaEntity(card);
        CardJpaEntity savedEntity = cardRepository.save(jpaEntity);
        return cardMapper.toDomainEntity(savedEntity);
    }

    @Override
    public Optional<Card> findById(String cardId) {
        Optional<CardJpaEntity> jpaEntityOptional = cardRepository.findByCardId(cardId);
        return jpaEntityOptional.map(cardMapper::toDomainEntity);
    }

    @Override
    public List<Card> findByLastUpdatedAfter(LocalDateTime dateTime, int page, int size) {
        // 注意：这里简化了分页实现，实际应使用Spring Data的Pageable
        List<CardJpaEntity> jpaEntities = cardRepository.findByLastUpdatedAfter(dateTime);

        // 分页处理
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, jpaEntities.size());
        if (startIndex >= jpaEntities.size()) {
            return List.of();
        }

        List<CardJpaEntity> pagedEntities = jpaEntities.subList(startIndex, endIndex);

        return pagedEntities.stream()
                .map(cardMapper::toDomainEntity)
                .collect(Collectors.toList());
    }
}    
