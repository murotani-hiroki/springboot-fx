package com.mrtn.fx.jpa.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mrtn.fx.DatabaseConfig;
import com.mrtn.fx.jpa.entity.TradeEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EntityScan(basePackages = {"com.mrtn.fx.jpa.entity"})
@EnableJpaRepositories(basePackages = {"com.mrtn.fx.*"})
//@ContextConfiguration(classes = {DatabaseConfig.class, TradeRepositoryTest.Config.class})
@ContextConfiguration(classes = {DatabaseConfig.class})
@AutoConfigureTestDatabase(replace = Replace.NONE) //
public class TradeRepositoryTest {

	/*
	@Configuration
	static class Config {
		@Bean
		public JdbcTemplate jdbcTemplateForTest(DataSource dataSource) {
			return new JdbcTemplate(dataSource);
		}
	}
	*/

	@Autowired
	TradeRepository tradeRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
//    @Sql({"/com/mrtn/fx/jpa/repository/TradeRepositoryTest.sql"})
	@Sql({"TradeRepositoryTest.sql"})
	@Transactional
	void findByTradingDateBetweenOrderByTradingDate() throws Exception {
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        Date from = formatter.parse("2021-01-01");
        Date to = formatter.parse("2021-01-31");
        List<TradeEntity> results = tradeRepository.findByTradingDateBetweenOrderByTradingDate(from, to);
        assertThat(results, hasSize(5));
	}

    // NG!  java.lang.IllegalArgumentException: Unknown entity: com.mrtn.fx.jpa.repository.TradeRepositoryTest$1
	//@Test
	void insert() {
		TradeEntity tradeEntity = new TradeEntity() {{ setId(2000); setTradingDate(new Date());}};
		tradeRepository.save(tradeEntity);
		assertTrue(true);
	}

}
