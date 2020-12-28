package com.mrtn.fx.service;

import com.mrtn.fx.model.CurrencyPair;
import com.mrtn.fx.model.Trade;
import com.mrtn.fx.web.SaveForm;
import com.mrtn.fx.web.SearchForm;

import java.util.List;

public interface FxService {

    /**
     * 一覧検索
     * @param form　検索条件
     * @return 検索結果
     */
    public List<Trade> search(SearchForm form);

    /**
     * IDで検索
     * @param id　TradeId
     * @return 検索結果
     */
    public Trade find(Integer id);

    /**
     * 通貨ペア一覧の取得
     * @return 通貨ペア一覧
     */
    public List<CurrencyPair> getCurrencyPairs();

    /**
     * 登録／更新
     * @param form 更新内容
     */
    public void save(SaveForm form);

    /**
     * 削除
     * @param deleteIds 削除対象ID
     */
    public void delete(String[] deleteIds);
}
