package com.mrtn.fx.service;

import com.mrtn.fx.model.CurrencyPair;
import com.mrtn.fx.model.Trade;
import com.mrtn.fx.mybatis.repository.TradeMapper;
import com.mrtn.fx.mybatis.repository.ext.CurrencyPairSearchMapper;
import com.mrtn.fx.mybatis.repository.ext.TradeSearchMapper;
import com.mrtn.fx.web.SaveForm;
import com.mrtn.fx.web.SearchForm;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FxServiceForMyBatis implements FxService {

    /** TradeMapper */
    @Autowired
    private TradeMapper tradeMapper;

    /** TradeSearchMapper */
    @Autowired
    private TradeSearchMapper tradeSearchMapper;

    /** CurrencyPairMapper */
    @Autowired
    private CurrencyPairSearchMapper currencyPairSearchMapper;

    /** ModelMapper */
    private ModelMapper modelMapper = new ModelMapper();

    /** Date Formatter */
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    private Logger logger = LoggerFactory.getLogger(FxServiceForMyBatis.class);

    /**
     * 一覧検索
     * @param form　検索条件
     * @return 検索結果
     */
    public List<Trade> search(SearchForm form) {
        //TODO formはDTOに移す。
        logger.info("fromDate: {}", form.getFromDate());
        logger.info("toDate: {}", form.getToDate());
        List<Trade> results = new ArrayList<>();
        tradeSearchMapper.findByTradingDate(form.getFromDate(), form.getToDate()).forEach(entity -> {
            results.add(modelMapper.map(entity, Trade.class));
        });
        return results;
    }

    /**
     * IDで検索
     * @param id　TradeId
     * @return 検索結果
     */
    public Trade find(Integer id) {
        com.mrtn.fx.mybatis.entity.Trade trade = tradeMapper.selectByPrimaryKey(id);
        Trade result = modelMapper.map(trade, Trade.class);
        return result;
    }

    /**
     * 通貨ペア一覧の取得
     * @return 通貨ペア一覧
     */
    public List<CurrencyPair> getCurrencyPairs() {
        List<CurrencyPair> results = new ArrayList<>();
        currencyPairSearchMapper.findAll().forEach(
                entity -> results.add(modelMapper.map(entity, CurrencyPair.class)));
        return results;
    }

    /**
     * 登録／更新
     * @param form 更新内容
     * @return 検索結果
     */
    public void save(SaveForm form) {
        //TODO formはDTOに移す。

        modelMapper.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(MappingContext<String, Date> context) {
                // 日付フォーマットを統一する。yyyyMMdd, yyyy/MM/dd → yyyy-MM-dd
                String unifiedDateStr = unifyDateFormat(context.getSource());
                try {
                    return formatter.parse(unifiedDateStr);
                } catch (ParseException e) {
                    return null;
                }
            }
        });
        com.mrtn.fx.mybatis.entity.Trade trade = modelMapper.map(form, com.mrtn.fx.mybatis.entity.Trade.class);

        if (trade.getId() == null) {
            tradeMapper.insertSelective(trade);
        } else {
            tradeMapper.updateByPrimaryKeySelective(trade);
        }
    }

    /**
     * 日付フォーマットを yyyy-MM-dd に統一する。
     * @param dateStr 日付文字列
     * @return 日付（yyyy-MM-dd）
     */
    private String unifyDateFormat(String dateStr) {
        // yyyyMMdd, yyyy/MM/dd を yyyy-MM-dd に変換する。
        return  dateStr.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3")
                       .replaceAll("(\\d{4})/(\\d{2})/(\\d{2})", "$1-$2-$3");
    }

    /**
     * 削除
     * @param deleteIds 削除対象ID
     * @return 検索結果
     */
    public void delete(String[] deleteIds) {
        for (String id : deleteIds) {
            tradeMapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
    }
}
