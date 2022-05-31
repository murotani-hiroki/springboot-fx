package com.mrtn.fx.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mrtn.fx.jpa.entity.CurrencyPairEntity;
import com.mrtn.fx.jpa.repository.CurrencyPairRepository;
import com.mrtn.fx.model.CurrencyPair;

@ExtendWith(MockitoExtension.class)
public class FxServiceForJpaTest {

	@InjectMocks
	FxServiceForJpa service;

	@Mock
	CurrencyPairRepository currencyPairRepository;

	@Test
	void getCurrencyPairs() throws Exception {
		List<CurrencyPairEntity> results = new ArrayList<CurrencyPairEntity>() {{
			add(new CurrencyPairEntity() {{ setId(1); setCurrencyPair("usd/jpy"); }});
			add(new CurrencyPairEntity() {{ setId(2); setCurrencyPair("eur/jpy"); }});
			add(new CurrencyPairEntity() {{ setId(3); setCurrencyPair("gbp/jpy"); }});
		}};
		doReturn(results).when(currencyPairRepository).findAll();

		List<CurrencyPair> actualResults = service.getCurrencyPairs();
		assertThat(actualResults).extracting("currencyPair").containsExactly("usd/jpy", "eur/jpy", "gbp/jpy");
	}

}
