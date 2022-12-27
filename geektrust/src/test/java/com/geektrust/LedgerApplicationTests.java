package com.geektrust;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LedgerApplicationTests {

		@Test
		public void GivenPrincipleRateAndYears_WhenGetEMI_ShouldReturnEMIAmount()
		{
			// Given
			int principleAmount = 2000, rate = 2, noOfYears = 2;
			EquatedMonthlyInstallment equatedMonthlyInstallment = new EquatedMonthlyInstallment(principleAmount, rate, noOfYears);

			// When
			int emiAmount = equatedMonthlyInstallment.GetEquatedMonthlyInstallment();

			// Then
			assertEquals(87, emiAmount);
		}


}
