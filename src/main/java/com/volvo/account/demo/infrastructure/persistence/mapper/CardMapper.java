package com.volvo.account.demo.infrastructure.persistence.mapper;


import com.volvo.account.demo.domain.entity.Card;
import com.volvo.account.demo.infrastructure.persistence.entity.CardJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public Card toDomainEntity(CardJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        Card card = new Card(
                jpaEntity.getCardId(),
                jpaEntity.getEmaid(),
                jpaEntity.getContractId()
        );
        card.setStatus(jpaEntity.getStatus());
        card.setCreatedAt(jpaEntity.getCreatedAt());
        card.setLastUpdated(jpaEntity.getLastUpdated());

        if (jpaEntity.getAccount() != null) {
            card.setAccountEmail(jpaEntity.getAccount().getEmail());
        }

        return card;
    }

    public CardJpaEntity toJpaEntity(Card domainEntity) {
        if (domainEntity == null) {
            return null;
        }

        CardJpaEntity jpaEntity = new CardJpaEntity(
                domainEntity.getCardId(),
                domainEntity.getEmaid(),
                domainEntity.getContractId(),
                domainEntity.getStatus(),
                domainEntity.getCreatedAt(),
                domainEntity.getLastUpdated()
        );

        // 注意：这里不设置Account关联，应由AccountJpaEntity来维护关联关系
        // 防止双向关联导致的无限循环

        return jpaEntity;
    }
}
