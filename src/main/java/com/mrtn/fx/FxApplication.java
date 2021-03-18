package com.mrtn.fx;

import com.mrtn.fx.service.FxService;
import com.mrtn.fx.service.FxServiceForJpa;
import com.mrtn.fx.service.FxServiceForMyBatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxApplication.class, args);
	}

	@Bean
	public FxService fxService() {
		return new FxServiceForJpa();          // for JPA use
		//return new FxServiceForMyBatis();    // for MyBatis use
	}
}
