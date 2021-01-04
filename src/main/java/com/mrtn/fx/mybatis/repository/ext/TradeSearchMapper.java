package com.mrtn.fx.mybatis.repository.ext;

import com.mrtn.fx.mybatis.entity.ext.TradeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeSearchMapper {

    public List<TradeList> findByTradingDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String ToDate
    );
}
