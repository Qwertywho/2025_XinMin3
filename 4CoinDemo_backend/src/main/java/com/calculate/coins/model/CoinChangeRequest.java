package com.calculate.coins.model;

import java.util.List;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CoinChangeRequest {
    @NotNull(message = "Target amount is required")
    @DecimalMin(value = "0.0", message = "Target amount must be greater than or equal to 0")
    @DecimalMax(value = "10000.00", message = "Target amount must be less than or equal to 10000.00")
    private double targetAmount;
    
    @NotNull(message = "Coin denominations are required")
    private List<Double> coinDenominations;
    
    public CoinChangeRequest() {
    }
    
    public CoinChangeRequest(double targetAmount, List<Double> coinDenominations) {
        this.targetAmount = targetAmount;
        this.coinDenominations = coinDenominations;
    }
    
    public double getTargetAmount() {
        return targetAmount;
    }
    
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }
    
    public List<Double> getCoinDenominations() {
        return coinDenominations;
    }
    
    public void setCoinDenominations(List<Double> coinDenominations) {
        this.coinDenominations = coinDenominations;
    }
}
