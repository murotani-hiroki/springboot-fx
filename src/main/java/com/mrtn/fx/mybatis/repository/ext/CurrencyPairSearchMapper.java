package com.mrtn.fx.mybatis.repository.ext;

import com.mrtn.fx.mybatis.entity.CurrencyPair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurrencyPairSearchMapper {
    //@Select("select * from currency_pair")
    public List<CurrencyPair> findAll();
}
