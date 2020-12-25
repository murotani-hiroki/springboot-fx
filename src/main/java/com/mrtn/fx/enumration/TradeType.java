package com.mrtn.fx.enumration;

import lombok.Getter;

public enum TradeType {
    Ask(1),
    Bid(2);

    @Getter
    private Integer id;
    private TradeType(Integer id) {
        this.id = id;
    }

    public static TradeType valueOf(Integer id) {
        for (TradeType tradeType : TradeType.values()) {
            if (tradeType.getId().equals(id)) {
                return tradeType;
            }
        }
        throw new RuntimeException("存在しない値です。");
    }
}
