package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

/**
 * 自動生成したTrade用のMapper
 */
@Mapper
public interface TradeMapper {
    /** 削除 */
    int deleteByPrimaryKey(Integer id);

    /** 未使用 */
    int insert(Trade record);

    /** 新規登録 */
    int insertSelective(Trade record);

    /** IDで検索 */
    Trade selectByPrimaryKey(Integer id);

    /** 更新 */
    int updateByPrimaryKeySelective(Trade record);

    /** 未使用 */
    int updateByPrimaryKey(Trade record);
}