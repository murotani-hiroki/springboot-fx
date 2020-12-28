package com.mrtn.fx.jpa.entity;

import lombok.Data;

@Data
/* jpa版コメント化
@Entity
@Table(name = "currency_pair")
*/
public class CurrencyPair {

    /** ID */
    /* jpa版コメント化
    @Id
    */
    private Integer id;

    /** 通貨ペア */
    private String currencyPair;


}
