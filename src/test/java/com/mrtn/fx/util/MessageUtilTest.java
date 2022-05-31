package com.mrtn.fx.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class MessageUtilTest {


	@Test
	public void testName() throws Exception {
		assertThat(1).as("1=1").isEqualTo(1);
	}

}
