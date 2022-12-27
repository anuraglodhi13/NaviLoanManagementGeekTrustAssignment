package com.geektrust;

import com.geektrust.exception.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class LoanTest {
    @Test
    public void GivenValidValuesForLoanInputText_WhenLoanIsCreated_ShouldMatchTheValues() throws InvalidInputException {
        // Given
        String inputText = "IDIDI Dale 10000 5 4";

        // When
        Loan loan = new Loan(inputText);

        // Then
        assertEquals("IDIDI", loan.BankName);
        assertEquals("Dale", loan.BorrowerName);
        assertEquals(10000, loan.Principal);
        assertEquals(5, loan.NoOfYears);
        assertEquals(4, loan.RateOfInterest);
    }

    @Test
    public void GivenInvalidValues_WhenLoanObjectCreated_ShouldThrowInvalidInputException()
    {
        String inputText1 = "IDIDI MissingRate 10000 5";
        String inputText2 = "IDIDI MissingRate 10000 5, 6, ExtraParameter";
        String inputText3 = "IDIDI Dale 10000 5 InvalidRate";


        // When

        Exception exception1 = assertThrows(InvalidInputException.class, () -> {
            Loan balance1 = new Loan(inputText1);
        });
        Exception exception2 = assertThrows(InvalidInputException.class, () -> {
            Loan balance2 = new Loan(inputText2);
        });
        Exception exception3 = assertThrows(InvalidInputException.class, () -> {
            Loan balance3 = new Loan(inputText3);
        });

        // Then
        assertEquals("The value IDIDI MissingRate 10000 5 provided for LOAN is not valid.", exception1.getMessage());
        assertEquals("The value IDIDI MissingRate 10000 5, 6, ExtraParameter provided for LOAN is not valid.", exception2.getMessage());
        assertEquals("The value IDIDI Dale 10000 5 InvalidRate provided for LOAN is not valid.", exception3.getMessage());


    }

}
