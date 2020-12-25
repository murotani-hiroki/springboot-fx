package com.mrtn.fx.web;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public abstract class BaseForm {

    /** エラー */
    private List<String> errors = new ArrayList<>();

    /** メッセージ（正常終了時） */
    private String message;
}
