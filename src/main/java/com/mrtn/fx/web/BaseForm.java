package com.mrtn.fx.web;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class BaseForm {

    /** エラー */
    private List<String> errors = new ArrayList<>();

    /** メッセージ（正常終了時） */
    private String message;
}
