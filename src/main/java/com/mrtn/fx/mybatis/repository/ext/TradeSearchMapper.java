package com.mrtn.fx.mybatis.repository.ext;

import com.mrtn.fx.mybatis.entity.ext.TradeListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Tradeの検索用Mapper
 */
@Mapper
public interface TradeSearchMapper {

    /** 検索 */
    public List<TradeListEntity> findByTradingDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String ToDate
    );
}
