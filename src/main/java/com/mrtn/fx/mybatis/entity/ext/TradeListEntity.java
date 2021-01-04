package com.mrtn.fx.mybatis.entity.ext;

import lombok.Data;

import java.util.Date;

/**
 * Trade検索用のEntity
 */
@Data
public class TradeListEntity {

    /** ID */
    private Integer id;

    /** 取引日 */
    private Date tradingDate;

    /** 決済日 */
    private Date settlementDate;

    /** 通貨ペアID */
    private Integer currencyPairId;

    /** 通貨ペア */
    private String currencyPair;

    /** Ask/Bid */
    private Integer tradeType;

    /** 数量 */
    private Integer quantity;

    /** Entry */
    private Float entryPrice;

    /** Exit */
    private Float exitPrice;

    /** ストップロス */
    private Integer stopLoss;

    /** 損益 */
    private Float profit;

    /** コメント */
    private String comment;
}
