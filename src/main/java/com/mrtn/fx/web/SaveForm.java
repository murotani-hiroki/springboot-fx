package com.mrtn.fx.web;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveForm extends BaseForm {

    /** ID */
    private String id;

    private String datePattern = "^([0-9]{4}/[0-9]{1,2}/[0-9]{1,2}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}|[0-9]{4}[0-9]{1,2}[0-9]{1,2})$";

    /** 取引日 */
    @NotBlank(message = "{E0001}", groups = ValidateGroups.First.class)
    @Pattern(regexp = "^([0-9]{4}/[0-9]{1,2}/[0-9]{1,2}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}|[0-9]{4}[0-9]{1,2}[0-9]{1,2})$", message = "{E0003}", groups = ValidateGroups.Second.class)
    private String tradingDate;

    /** 決済日 */
    @NotBlank(message = "{E0002}", groups = ValidateGroups.First.class)
    @Pattern(regexp = "^([0-9]{4}/[0-9]{1,2}/[0-9]{1,2}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}|[0-9]{4}[0-9]{1,2}[0-9]{1,2})$", message = "{E0004}", groups = ValidateGroups.Second.class)
    private String settlementDate;

    /** 通貨ペアID */
    private String  currencyPairId;

    /** Ask/Bid */
    private String tradeType;

    /** 数量 */
    private String quantity;

    /** Entry */
    private String entryPrice;

    /** Exit */
    private String exitPrice;

    /** ストップロス */
    private String stopLoss;

    /** 損益 */
    private String profit;

    /** コメント */
    private String comment;
}
