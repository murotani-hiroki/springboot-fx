package com.mrtn.fx.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "trade")
public class TradeEntity {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 取引日 */
    private Date tradingDate;

    /** 決済日 */
    private Date settlementDate;

    /** 通貨ペアID */
    //private Integer currencyPairId;

    /** 通貨ペア */
    @ManyToOne
    @JoinColumn(name = "currency_pair_id")
    private CurrencyPairEntity currencyPair;

    /** Ask/Bid */
    //TODO enum化する。
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
