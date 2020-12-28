package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.Trade;

import java.util.Date;
import java.util.List;

/* jpa版コメント化
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
*/
public interface TradeRepository {

    /** trading_date で検索して trading_date の昇順でソート */
    public List<Trade> findByTradingDateBetweenOrderByTradingDate(Date from, Date To);
}
