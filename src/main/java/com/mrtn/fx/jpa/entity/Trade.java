package com.mrtn.fx.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "trade")
public class Trade {

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
    private CurrencyPair currencyPair;

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
