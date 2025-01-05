package com.calculate.coins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calculate.coins.model.CoinChangeRequest;
import com.calculate.coins.service.CoinChangeService;
import com.calculate.coins.validation.CoinDenominationValidator;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CoinChangeController {

    private CoinChangeService coinChangeService;

    @Autowired
    public CoinChangeController(CoinChangeService coinChangeService) {
        this.coinChangeService = coinChangeService;
    }

    @PostMapping("/api/coins/minCoins")
    public ResponseEntity<StringBuilder> getMinCoins(@RequestBody CoinChangeRequest request) {
        double targetAmount = request.getTargetAmount();

        if (targetAmount < 0 || targetAmount > 10000) {
            return ResponseEntity.badRequest().body(new StringBuilder("The target amount should be between 0 and 10,000.00."));
        }

        //validate coin denominations using validator class
        if (!CoinDenominationValidator.isValid(request.getCoinDenominations())) {
            return ResponseEntity.badRequest().body(new StringBuilder("Invalid coin denominations provided. This is the list of valid denominations [0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0]."));
        }

        //proceed with the processing if valid
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, request.getCoinDenominations());
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (Double coin : result) {
            output.append(coinChangeService.formatCurrencyForDisplay(coin)).append(", ");
        }

        //remove last comma and space if output is not empty
        if (output.length() > 0) {
            output.setLength(output.length() - 2);//remove last comma and space
        }
        output.append("]");

        if (output.isEmpty()) {
            return ResponseEntity.ok(new StringBuilder()); //return an empty list if no solution found
        }

        //convert stringbuilder to string and return in ResponseEntity
        return ResponseEntity.ok(output);
    }
}
