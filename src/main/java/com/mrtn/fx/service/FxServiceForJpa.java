package com.mrtn.fx.service;

import com.mrtn.fx.jpa.entity.TradeEntity;
import com.mrtn.fx.jpa.repository.CurrencyPairRepository;
import com.mrtn.fx.jpa.repository.TradeRepository;
import com.mrtn.fx.model.CurrencyPair;
import com.mrtn.fx.model.Trade;
import com.mrtn.fx.web.SaveForm;
import com.mrtn.fx.web.SearchForm;
import org.apache.logging.log4j.util.Strings;
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

public class FxServiceForJpa implements FxService {

    /** TradeRepository */
    @Autowired
    private TradeRepository tradeRepository;

    /** CurrencyPairRepository */
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    /** ModelMapper */
    private ModelMapper modelMapper = new ModelMapper();

    /** Date Formatter */
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Logger logger = LoggerFactory.getLogger(FxServiceForJpa.class);

    /**
     * 一覧検索
     * @param form　検索条件
     * @return 検索結果
     */
    public List<Trade> search(SearchForm form) {
        //TODO formはDTOに移す。
        // From To の指定が無ければ、Min値 Max値 で検索する。
        logger.info("fromDate: {}", form.getFromDate());
        logger.info("toDate: {}", form.getToDate());
        String fromDateStr = Strings.isNotBlank(form.getFromDate()) ? form.getFromDate() : "1970-01-01";
        String toDateStr = Strings.isNotBlank(form.getToDate()) ? form.getToDate() : "9999-12-31";
        Date fromDate = null;
        Date toDate = null;
        try {
            // 日付フォーマットを yyyy-MM-dd に統一してDateを生成する。
            fromDate = formatter.parse(unifyDateFormat(fromDateStr));
            toDate = formatter.parse(unifyDateFormat(toDateStr));
        } catch (ParseException e) {
            throw new RuntimeException("日付の入力が不正です。", e);
        }
        List<Trade> results = new ArrayList<>();
        //tradeRepository.findAll().forEach(entity -> {
        tradeRepository.findByTradingDateBetweenOrderByTradingDate(fromDate, toDate).forEach(entity -> {
            Trade trade = modelMapper.map(entity, Trade.class);
            trade.setCurrencyPairId(entity.getCurrencyPair().getId());
            trade.setCurrencyPair(entity.getCurrencyPair().getCurrencyPair());
            //trade.setTradeType(TradeType.valueOf(entity.getTradeType()).toString());
            results.add(trade);
        });
        return results;
    }

    /**
     * IDで検索
     * @param id　TradeId
     * @return 検索結果
     */
    public Trade find(Integer id) {
        TradeEntity tradeEntity = tradeRepository.findById(id).get();
        Trade result = modelMapper.map(tradeEntity, Trade.class);
        return result;
    }

    /**
     * 通貨ペア一覧の取得
     * @return 通貨ペア一覧
     */
    public List<CurrencyPair> getCurrencyPairs() {
        List<CurrencyPair> results = new ArrayList<>();
        currencyPairRepository.findAll().forEach(
                entity -> results.add(modelMapper.map(entity, CurrencyPair.class)));
        return results;
    }

    /**
     * 登録／更新
     * @param form 更新内容
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

        TradeEntity tradeEntity = modelMapper.map(form, TradeEntity.class);
        tradeRepository.save(tradeEntity);
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
     */
    public void delete(String[] deleteIds) {
        for (String id : deleteIds) {
            tradeRepository.deleteById(Integer.valueOf(id));
        }
    }
}
