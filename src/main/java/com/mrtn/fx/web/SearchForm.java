package com.mrtn.fx.web;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class SearchForm extends BaseForm {

    /** 検索条件 From */
    @Pattern(regexp = "^(^$|[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}|[0-9]{4}[0-9]{1,2}[0-9]{1,2})$", message = "{E0003}")
    private String fromDate;

    /** 検索条件 To */
    @Pattern(regexp = "^(^$|[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}|[0-9]{4}[0-9]{1,2}[0-9]{1,2})$", message = "{E0003}")
    private String toDate;

}
