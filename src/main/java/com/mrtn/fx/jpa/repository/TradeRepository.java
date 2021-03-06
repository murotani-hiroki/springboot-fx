package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Integer> {

    /** trading_date で検索して trading_date の昇順でソート */
    public List<TradeEntity> findByTradingDateBetweenOrderByTradingDate(Date from, Date To);
}
