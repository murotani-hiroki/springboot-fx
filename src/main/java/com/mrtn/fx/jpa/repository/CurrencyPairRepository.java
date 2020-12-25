package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.CurrencyPair;
import com.mrtn.fx.jpa.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CurrencyPairRepository extends JpaRepository<CurrencyPair, Integer> {
}
