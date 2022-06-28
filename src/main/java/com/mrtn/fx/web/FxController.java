package com.mrtn.fx.web;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mrtn.fx.model.CurrencyPair;
import com.mrtn.fx.model.Trade;
import com.mrtn.fx.service.FxService;
import com.mrtn.fx.util.MessageUtil;

@Controller
public class FxController {

    @Autowired
    private FxService fxService;

    //※MessageUtil に実装している
    //@Autowired
    //private MessageSource messageSource;

    Logger logger = LoggerFactory.getLogger(FxController.class);

    /**
     * トップページ
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping(value = "/")
    public ModelAndView top(ModelAndView mv) {
        mv.setViewName("main");
        return mv;
    }

    /**
     * 一覧検索
     * @param form フォーム
     * @param bindingResult BidingResult
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(@Validated SearchForm form,BindingResult bindingResult ,ModelAndView mv) {
        logger.info("--- search start ---");
        // 入力チェック
        if (!validate(form, bindingResult)) {
            // エラーの場合、form にセットされたエラーを返す。
            mv.addObject("errors", form.getErrors());
            mv.setViewName("error::errors");
            return mv;
        }

        // 正常の場合、一覧検索を行う。
        List<Trade> trades = fxService.search(form);
        logger.info("検索結果 : {}件", trades.size());
        mv.addObject("trades",trades);
        mv.setViewName("trade_list::trades");
        logger.info("--- search end ---");
        return mv;
    }

    /**
     * 削除
     * @param deleteIds 削除対象のID
     * @param mv ModelAndView
     * @return メッセージ
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "deleteIds[]") String[] deleteIds, ModelAndView mv) {
        logger.info("--- delete start ---");
        fxService.delete(deleteIds);
        logger.info("--- delete end ---");
        //return messageSource.getMessage("M0002", null, Locale.getDefault());
        return MessageUtil.getMessage("M0002");
    }


    /**
     * モーダルウィンドウの新規表示
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping(value = "/new")
    public ModelAndView newModal(ModelAndView mv) {
        logger.info("--- new start ---");
        // 通貨ペアの一覧を取得する。
        List<CurrencyPair> currencyPairs = fxService.getCurrencyPairs();
        logger.info("通貨ペア取得 : {}件", currencyPairs.size());
        mv.addObject("currencyPairs", currencyPairs);
        mv.setViewName("modal::modal");
        logger.info("--- new end ---");
        return mv;
    }

    /**
     * モーダルウィンドウの編集表示
     * @param id 選択されたTradeのID
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping(value = "/edit")
    public ModelAndView editModal(@RequestParam Integer id, ModelAndView mv) {
        logger.info("--- edit start ---");
        // 通貨ペアの一覧を取得する。
        List<CurrencyPair> currencyPairs = fxService.getCurrencyPairs();
        logger.info("通貨ペア取得 : {}件", currencyPairs.size());
        // 選択されたTradeを取得する。
        Trade trade = fxService.find(id);
        logger.info("Trade取得 : {}件", trade == null ? 0 : 1);
        mv.addObject("currencyPairs", currencyPairs);
        mv.addObject("trade", trade);
        mv.setViewName("modal::modal");
        logger.info("--- edit end ---");
        return mv;
    }

    /**
     * モーダルウィンドウ save
     * @param form フォーム
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public SaveForm save(@Validated(ValidateGroups.All.class) SaveForm form, BindingResult bindingResult, ModelAndView mv) {
        logger.info("--- save start ---");
        // 入力チェック
        if (!validate(form, bindingResult)) {
            // エラーの場合、form にエラーメッセージがセットされる。
            return form;
        }

        // 正常の場合、更新処理を行い、form に完了メッセージをセットする。
        fxService.save(form);
        form.setMessage(MessageUtil.getMessage("M0001"));
        logger.info("--- save end ---");
        return form;
    }

    /**
     * Validationの結果を判定する。
     * @param form フォーム
     * @param bindingResult BindingResult
     * @return エラーの場合 false
     */
    private boolean validate(BaseForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // エラーの場合
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                logger.info("{} : {}", fieldError.getField(), fieldError.getDefaultMessage());
                // tradingDate=取引日 の定義から「取引日」を取得する。
                String fieldName = MessageUtil.getMessage(fieldError.getField());
                // {0}を入力して下さい → 取引日を入力して下さい
                String errorMssage = MessageFormat.format(fieldError.getDefaultMessage(), fieldName);
                form.getErrors().add(errorMssage);
            }
            return false;
        }

        // 正常の場合
        return true;
    }

}
