package com.volvo.account.demo.infrastructure.emaid;

import java.util.UUID;

public class DefaultEmaidGenerator implements EmaidGenerator{
    @Override
    public String generate(String contractId) {
        // 简化的EMAID生成逻辑
        // 实际应该根据规范生成符合格式的EMAID
        String randomPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return String.format("EV%S.%S", contractId.substring(0, 3), randomPart);
    }
}
