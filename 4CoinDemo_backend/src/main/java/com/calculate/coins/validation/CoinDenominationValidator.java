package com.calculate.coins.validation;

import java.util.Arrays;
import java.util.List;

public class CoinDenominationValidator {

	private static List<Double> ALLOWED_DENOMINATIONS = Arrays.asList(0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0);
	
	public static boolean isValid(List<Double> coinDenominations) {
		if (coinDenominations == null || coinDenominations.isEmpty()) {
			return false;
		}
		for (Double denomination: coinDenominations) {
			if (!ALLOWED_DENOMINATIONS.contains(denomination)) {
				return false; //invalid denomination found
			}			
		}
		return true; //all denominations are valid
	}
}
