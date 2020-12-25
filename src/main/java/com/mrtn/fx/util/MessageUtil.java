package com.mrtn.fx.util;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public final class MessageUtil {

    private static MessageSource messageSource;

    static {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasename("classpath:messages");
        messageSource = resourceBundleMessageSource;
    }

    /**
     * メッセージを取得する
     * @param code メッセージID
     * @return メッセージ
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * メッセージを取得する
     * @param code メッセージID
     * @param params パラメータ
     * @return メッセージ
     */
    public static String getMessage(String code, String[] params) {
        return messageSource.getMessage(code, params, Locale.getDefault());
    }

}
