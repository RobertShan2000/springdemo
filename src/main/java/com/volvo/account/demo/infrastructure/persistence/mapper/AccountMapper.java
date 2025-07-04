package com.volvo.account.demo.infrastructure.persistence.mapper;

import com.volvo.account.demo.domain.entity.Account;
import com.volvo.account.demo.domain.entity.Card;
import com.volvo.account.demo.infrastructure.persistence.entity.AccountJpaEntity;
import com.volvo.account.demo.infrastructure.persistence.entity.CardJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public Account toDomainEntity(AccountJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        Account account = new Account(jpaEntity.getEmail());
        account.setStatus(jpaEntity.getStatus());
        account.setCreatedAt(jpaEntity.getCreatedAt());
        account.setLastUpdated(jpaEntity.getLastUpdated());

        // 转换关联的卡片
        List<Card> cards = new ArrayList<>();
        for (CardJpaEntity cardJpaEntity : jpaEntity.getCards()) {
            cards.add(cardMapper.toDomainEntity(cardJpaEntity));
        }
        account.setCards(cards);

        return account;
    }

    public AccountJpaEntity toJpaEntity(Account domainEntity) {
        if (domainEntity == null) {
            return null;
        }

        AccountJpaEntity jpaEntity = new AccountJpaEntity(
                domainEntity.getEmail(),
                domainEntity.getStatus(),
                domainEntity.getCreatedAt(),
                domainEntity.getLastUpdated()
        );

        // 转换关联的卡片
        List<CardJpaEntity> cardJpaEntities = new ArrayList<>();
        for (Card card : domainEntity.getCards()) {
            CardJpaEntity cardJpaEntity = cardMapper.toJpaEntity(card);
            cardJpaEntity.setAccount(jpaEntity);
            cardJpaEntities.add(cardJpaEntity);
        }
        jpaEntity.setCards(cardJpaEntities);

        return jpaEntity;
    }

    // 注入CardMapper
    private final CardMapper cardMapper;

    public AccountMapper(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }
}
