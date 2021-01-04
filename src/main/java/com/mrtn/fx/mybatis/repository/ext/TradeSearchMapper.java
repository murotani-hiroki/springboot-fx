package com.mrtn.fx.mybatis.repository.ext;

import com.mrtn.fx.mybatis.entity.ext.TradeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Tradeの検索用Mapper
 */
@Mapper
public interface TradeSearchMapper {

    /** 検索 */
    public List<TradeList> findByTradingDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String ToDate
    );
}
