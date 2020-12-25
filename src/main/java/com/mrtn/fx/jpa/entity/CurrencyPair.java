package com.mrtn.fx.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
