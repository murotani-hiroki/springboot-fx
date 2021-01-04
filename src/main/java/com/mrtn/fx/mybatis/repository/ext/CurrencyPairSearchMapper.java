package com.mrtn.fx.mybatis.repository.ext;

import com.mrtn.fx.mybatis.entity.CurrencyPair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CurrencyPairの検索用Mapper
 */
@Mapper
public interface CurrencyPairSearchMapper {
    /** 検索 */
    public List<CurrencyPair> findAll();
}
