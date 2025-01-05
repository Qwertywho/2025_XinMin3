package com.calculate.coins;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.calculate.coins.model.CoinChangeRequest;
import com.calculate.coins.service.CoinChangeService;

public class CoinChangeServiceTest {

	@InjectMocks
	private CoinChangeService coinChangeService; //The service being
	
	@Mock
	private CoinChangeRequest request;
	
	@BeforeEach 
	public void setUp() {
		MockitoAnnotations.openMocks(this); //initialise mocks
	}
	
	@Test
	void testCalculateMinCoins() {
		//Given
		double targetAmount = 103;
		List<Double> coinDenominations = Arrays.asList(1.0, 2.0, 50.0);
		
		//mock behaviour of request object
		when(request.getTargetAmount()).thenReturn(targetAmount); 
		when(request.getCoinDenominations()).thenReturn(coinDenominations);
		
		//When
		List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
		
		//Then
		assertThat(result).isNotNull();
		assertThat(result.stream().mapToDouble(Double::doubleValue).sum()).isEqualTo(targetAmount);
	}
	
	@Test
	void testCalculateMinCoins_ExactChange() {
        // Given
        double targetAmount = 7.03;
        List<Double> coinDenominations = Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0);
        
        // When
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
        
        // Then
        assertThat(result)
            .containsExactly(0.01, 0.01, 0.01, 1.0, 1.0, 5.0)
            .hasSize(6);
        assertThat(result.stream().mapToDouble(Double::doubleValue).sum()).isEqualTo(targetAmount);
    }
    
    @Test
    void testCalculateMinCoins_LargeAmount() {
        // Given
        double targetAmount = 103;
        List<Double> coinDenominations = Arrays.asList(1.0, 2.0, 50.0);
        
        // When
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
        
        // Then
        assertThat(result.stream().mapToDouble(Double::doubleValue).sum())
            .isEqualTo(targetAmount);
       
        assertThat(result).contains(2.0);
    }
    
    @Test
    void testCalculateMinCoins_NoSolution() {
        // Given
        double targetAmount = 0.23;
        List<Double> coinDenominations = Arrays.asList(0.5); // Only quarter available
        
        // When
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
        
        // Then
        assertThat(result).isEmpty();
    }
    
    @Test
    void testCalculateMinCoins_ZeroAmount() {
        // Given
        double targetAmount = 0.0;
        List<Double> coinDenominations = Arrays.asList(0.2, 0.10, 0.05, 0.01);
        
        // When
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
        
        // Then
        assertThat(result).isEmpty();
    }
    
    @Test
    void testCalculateMinCoins_SingleCoin() {
        // Given
        double targetAmount = 0.2;
        List<Double> coinDenominations = Arrays.asList(0.2, 0.1, 0.05, 0.01);
        
        // When
        List<Double> result = coinChangeService.calculateMinCoins(targetAmount, coinDenominations);
        
        // Then
        assertThat(result)
            .containsExactly(0.2)
            .hasSize(1);
    }
}
