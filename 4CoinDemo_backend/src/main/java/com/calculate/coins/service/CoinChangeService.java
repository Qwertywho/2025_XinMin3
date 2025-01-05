package com.calculate.coins.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CoinChangeService {

    public List<Double> calculateMinCoins(double targetAmount, List<Double> coinDenominations) {
        //convert targetAmount to cents
        int amountInCents = (int) Math.round(targetAmount * 100);
        //convert coin denominations from dollars to cents
        int[] coinsInCents = new int[coinDenominations.size()];
        for (int i = 0; i < coinDenominations.size(); i++) {
            coinsInCents[i] = (int) Math.round(coinDenominations.get(i) * 100);
        }
        //sort in ascending
        Arrays.sort(coinsInCents);

        List<Double> result = new ArrayList<>();
        for (int i = coinsInCents.length - 1; i >= 0; i--) {
            int pieces = 0;
            if (amountInCents == 0) {
                break; //No more amount left to change
            }
            //use modulus to find how many coins of each denomination we can use
            if (amountInCents >= coinsInCents[i]) {
                pieces += amountInCents / coinsInCents[i]; //count how many coins of this denomination
                amountInCents %= coinsInCents[i]; //update remaining amount
                for (int j = 0; j < pieces; j++) {
                    result.add((double) coinsInCents[i]);
                }
            }
        }

        //reconstruct the solution
        List<Double> resultDollars = new ArrayList<>();
        //int remaining = amountInCents;
        for (double cents : result) {
            double dollars = cents / 100.0;
            resultDollars.add(dollars);
        }

        Collections.sort(resultDollars);
        return resultDollars;
    }

    public String formatCurrencyForDisplay(double amount) {
        //check if amount is a whole number
        if (amount == Math.floor(amount)) {
            return String.format("%.0f", amount); //return as an integer
        } else {
            return String.format("%.2f", amount); //return as decimal values
        }
    }
}
