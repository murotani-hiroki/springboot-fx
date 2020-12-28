package com.mrtn.fx.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currency_pair")
public class CurrencyPair {

    /** ID */
    @Id
    private Integer id;

    /** 通貨ペア */
    private String currencyPair;


}
