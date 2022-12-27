package com.geektrust;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterestTest {

    @Test
    public void GivenSampleRateNoOfYearsAndPrincipleAmount_WhenGetInterest_ShouldReturnExpectedValue()
    {
        // Given
        int rate = 2, noOfYears = 2, principleAmount = 2000;
        Interest interest = new Interest(rate, noOfYears, principleAmount);

        // When
        int calculatedInterest = interest.GetInterest();

        // Then
        assertEquals(80, calculatedInterest);
    }
}
