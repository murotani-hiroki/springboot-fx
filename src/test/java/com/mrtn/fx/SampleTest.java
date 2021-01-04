package com.mrtn.fx;

import com.mrtn.fx.jpa.entity.TradeEntity;
import com.mrtn.fx.web.SaveForm;
import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class SampleTest {

	@Test
	void debug1() throws Exception {

		SaveForm form = new SaveForm();
		form.setTradingDate("20201224");
		form.setSettlementDate("2020/12/25");
		form.setTradeType("2");
		form.setEntryPrice("105.20");
		form.setComment("Hello!");
		//form.setCurrencyPairId("7");

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new Converter<String, Date>() {
		    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			@Override
			public Date convert(MappingContext<String, Date> context) {
			    // 日付フォーマットを統一する。yyyyMMdd, yyyy/MM/dd → yyyy-MM-dd
				String source = context.getSource();
				String replaced = source.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3")
										.replaceAll("(\\d{4})/(\\d{2})/(\\d{2})", "$1-$2-$3");
				try {
					return formatter.parse(replaced);
				} catch (ParseException e) {
					return null;
				}
			}
		});
		TradeEntity trade = modelMapper.map(form, TradeEntity.class);

		assertThat(trade.getTradingDate(), is(new SimpleDateFormat("yyyyMMdd").parse("20201224")));
		assertThat(trade.getSettlementDate(), is(new SimpleDateFormat("yyyyMMdd").parse("20201225")));
		assertThat(trade.getTradeType(), is(Integer.valueOf(2)));
		assertThat(trade.getEntryPrice(), is(Float.valueOf("105.20")));
		assertThat(trade.getComment(), is("Hello!"));
		//assertThat(trade.getCurrencyPairId(), is(Integer.valueOf(7)));
	}

	@Test
	void debug2() {
		String src = "2020-12-24";
		String replaced = src.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3")
							 .replaceAll("(\\d{4})/(\\d{2})/(\\d{2})", "$1-$2-$3");
		assertThat(replaced, is("2020-12-24"));
	}

}
