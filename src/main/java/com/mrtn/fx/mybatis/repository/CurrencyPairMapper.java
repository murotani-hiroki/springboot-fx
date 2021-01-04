package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.CurrencyPair;

public interface CurrencyPairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrencyPair record);

    int insertSelective(CurrencyPair record);

    CurrencyPair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrencyPair record);

    int updateByPrimaryKey(CurrencyPair record);
}