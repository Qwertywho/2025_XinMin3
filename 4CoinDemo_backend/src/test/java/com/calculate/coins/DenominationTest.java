package com.calculate.coins;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.calculate.coins.validation.CoinDenominationValidator;


public class DenominationTest {

	@Test
	public void testValidCoinDenominations() {
		List<Double> validDenominations = Arrays.asList(0.01, 1.0, 2.0);
		assertTrue(CoinDenominationValidator.isValid(validDenominations), "Should be valid denominations");
	}
	
}
