package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    /** trading_date で検索して trading_date の昇順でソート */
    public List<Trade> findByTradingDateBetweenOrderByTradingDate(Date from, Date To);
}
