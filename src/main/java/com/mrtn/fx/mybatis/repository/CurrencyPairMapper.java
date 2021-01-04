package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.CurrencyPair;
import org.apache.ibatis.annotations.Mapper;

/**
 * 自動生成したCurrencyPair用のMapper
 * 未使用
 */
@Mapper
public interface CurrencyPairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrencyPair record);

    int insertSelective(CurrencyPair record);

    CurrencyPair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrencyPair record);

    int updateByPrimaryKey(CurrencyPair record);
}