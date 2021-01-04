package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.CurrencyPair;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CurrencyPairRepository extends JpaRepository<CurrencyPair, Integer> {}
