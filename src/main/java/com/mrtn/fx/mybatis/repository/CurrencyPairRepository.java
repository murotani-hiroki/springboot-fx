package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.CurrencyPair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CurrencyPairRepository {
    //@Select("select * from currency_pair")
    public List<CurrencyPair> findAll();
}
