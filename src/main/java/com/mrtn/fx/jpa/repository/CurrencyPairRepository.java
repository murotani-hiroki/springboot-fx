package com.mrtn.fx.jpa.repository;

import com.mrtn.fx.jpa.entity.CurrencyPairEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CurrencyPairRepository extends JpaRepository<CurrencyPairEntity, Integer> {}
