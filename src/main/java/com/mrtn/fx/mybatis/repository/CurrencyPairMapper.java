package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.CurrencyPairEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 自動生成したCurrencyPair用のMapper
 * 未使用
 */
@Mapper
public interface CurrencyPairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrencyPairEntity record);

    int insertSelective(CurrencyPairEntity record);

    CurrencyPairEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrencyPairEntity record);

    int updateByPrimaryKey(CurrencyPairEntity record);
}