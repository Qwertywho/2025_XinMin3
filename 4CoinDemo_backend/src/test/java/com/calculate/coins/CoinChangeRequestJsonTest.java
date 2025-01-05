package com.calculate.coins;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.List;

import com.calculate.coins.model.CoinChangeRequest;

@JsonTest
public class CoinChangeRequestJsonTest {

	@Autowired
	private JacksonTester<CoinChangeRequest> jacksonTester;
	@Test
	void serialiseCoinChangeRequest() throws IOException{
		//Given
		CoinChangeRequest request = new CoinChangeRequest();
		request.setTargetAmount(7.03);
		request.setCoinDenominations(List.of(1.0,2.0,50.0));
		
		//When
		var json = jacksonTester.write(request);
		//Then
		assertThat(json).hasJsonPathValue("$.targetAmount", 103);
		assertThat(json).hasJsonPathValue("$.coinDenominations[0]", 1.0);
		assertThat(json).hasJsonPathValue("$.coinDenominations[1]", 2.0);
		assertThat(json).hasJsonPathValue("$.coinDenominations[2]", 50.0);
	}

	@Test
	void deserializeCoinChangeRequest() throws IOException {
		//Given
		String jsonInput = "{\"targetAmount\":103,\"coinDenominations\":[1,2,50]}";
		//When
		CoinChangeRequest request = jacksonTester.parse(jsonInput).getObject();
		
		//Then
		assertThat(request.getTargetAmount()).isEqualTo(103);
		assertThat(request.getCoinDenominations()).containsExactly(1.0, 2.0, 50.0);
	}
}
