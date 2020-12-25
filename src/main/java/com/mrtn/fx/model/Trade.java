package com.mrtn.fx.model;

import com.mrtn.fx.enumration.TradeType;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Trade {

    /** 日付表示用のフォーマット */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    /** ID */
    private Integer id;

    /** 取引日 */
    private Date tradingDate;

    /** 決済日 */
    private Date settlementDate;

    /** 通貨ペアID */
    private Integer  currencyPairId;

    /** 通貨ペア */
    private String  currencyPair;

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

    /**
     * Ask or Bid の文字列を返す
     * @return Ask or Bid
     */
    public String getTradeTypeStr() {
        return TradeType.valueOf(tradeType).toString();
    }

    /**
     * 取引日をフォーマットして返す。
     * @return 取引日
     */
    public String getTradingDateStr() {
        return formatter.format(tradingDate);
    }

    /**
     * 決済日をフォーマットして返す。
     * @return 決済日
     */
    public String getSettlementDateStr() {
        return formatter.format(settlementDate);
    }
}

