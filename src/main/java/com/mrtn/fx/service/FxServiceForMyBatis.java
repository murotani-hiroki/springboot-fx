package com.mrtn.fx.service;

import com.mrtn.fx.model.CurrencyPair;
import com.mrtn.fx.model.Trade;
import com.mrtn.fx.mybatis.repository.CurrencyPairMapper;
import com.mrtn.fx.mybatis.repository.TradeMapper;
import com.mrtn.fx.web.SaveForm;
import com.mrtn.fx.web.SearchForm;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FxServiceForMyBatis implements FxService {

    /** TradeRepository */
    @Autowired
    private TradeMapper tradeMapper;

    /** CurrencyPairRepository */
    @Autowired
    private CurrencyPairMapper currencyPairMapper;

    /** ModelMapper */
    private ModelMapper modelMapper = new ModelMapper();

    /** Date Formatter */
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 一覧検索
     * @param form　検索条件
     * @return 検索結果
     */
    public List<Trade> search(SearchForm form) {
        //TODO formはDTOに移す。
        // From To の指定が無ければ、Min値 Max値 で検索する。
        String fromDate = Strings.isNotBlank(form.getFromDate()) ? form.getFromDate() : "1970-01-01";
        String toDate = Strings.isNotBlank(form.getToDate()) ? form.getToDate() : "9999-12-31";
        List<Trade> results = new ArrayList<>();
        tradeMapper.findByTradingDate(fromDate, toDate).forEach(entity -> {
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
        /*
        com.mrtn.fx.jpa.entity.Trade trade = tradeRepository.findById(id).get();
        Trade result = modelMapper.map(trade, Trade.class);
        return result;
         */
        return null;
    }

    /**
     * 通貨ペア一覧の取得
     * @return 通貨ペア一覧
     */
    public List<CurrencyPair> getCurrencyPairs() {
        List<CurrencyPair> results = new ArrayList<>();
        currencyPairMapper.findAll().forEach(
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
        com.mrtn.fx.jpa.entity.Trade trade = modelMapper.map(form, com.mrtn.fx.jpa.entity.Trade.class);

        //tradeRepository.save(trade);
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
            //tradeRepository.deleteById(Integer.valueOf(id));
        }
    }
}
