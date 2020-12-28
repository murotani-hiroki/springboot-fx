package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TradeRepository {

    public List<Trade> findByTradingDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String ToDate
    );
}
