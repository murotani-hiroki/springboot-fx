package com.mrtn.fx.mybatis.repository;

import com.mrtn.fx.mybatis.entity.TradeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 自動生成したTrade用のMapper
 */
@Mapper
public interface TradeMapper {
    /** 削除 */
    int deleteByPrimaryKey(Integer id);


    /** 未使用 */
    int insert(TradeEntity record);

    /** 新規登録 */
    int insertSelective(TradeEntity record);

    /** IDで検索 */
    TradeEntity selectByPrimaryKey(Integer id);

    /** 更新 */
    int updateByPrimaryKeySelective(TradeEntity record);

    /** 未使用 */
    int updateByPrimaryKey(TradeEntity record);
}