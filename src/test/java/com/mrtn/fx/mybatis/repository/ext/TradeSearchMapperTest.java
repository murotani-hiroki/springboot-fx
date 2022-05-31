package com.mrtn.fx.mybatis.repository.ext;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mrtn.fx.mybatis.entity.ext.TradeListEntity;

/**
 * NG!
 * @MyBatisTest 用に追加ライブラリが必要らしい
 */
@ExtendWith(SpringExtension.class)
public class TradeSearchMapperTest {

	@Autowired
	TradeSearchMapper tradeSearchMapper;

	@Test
	void findByTradingDate() throws Exception {
		List<TradeListEntity> results = tradeSearchMapper .findByTradingDate("2020-12-25", "2020-12-29");
		assertThat(results, hasSize(3));
	}

}
