package com.geektrust;

import com.geektrust.exception.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PaymentTest {
    @Test
    public void GivenValidValuesForPaymentInputText_WhenPaymentIsCreated_ShouldMatchTheValues() throws InvalidInputException {
        // Given
        String inputText = "IDIDI Dale 5000 5";

        // When
        Payment payment = new Payment(inputText);

        // Then
        assertEquals("IDIDI", payment.BankName);
        assertEquals("Dale", payment.BorrowerName);
        assertEquals(5000, payment.LumpsumAmount);
        assertEquals(5, payment.EmiNo);
    }

    @Test
    public void GivenInvalidValues_WhenPaymentObjectCreated_ShouldThrowInvalidInputException()
    {
        String inputText1 = "IDIDI MissingEmi 5000";
        String inputText2 = "IDIDI Dale 10000 InvalidEmi";
        String inputText3 = "IDIDI Dale 5000 5 ExtraParameter";

        // When

        Exception exception1 = assertThrows(InvalidInputException.class, () -> {
            Payment balance1 = new Payment(inputText1);
        });
        Exception exception2 = assertThrows(InvalidInputException.class, () -> {
            Payment balance2 = new Payment(inputText2);
        });
        Exception exception3 = assertThrows(InvalidInputException.class, () -> {
            Payment balance3 = new Payment(inputText3);
        });

        // Then
        assertEquals("The value IDIDI MissingEmi 5000 provided for PAYMENT is not valid.", exception1.getMessage());
        assertEquals("The value IDIDI Dale 10000 InvalidEmi provided for PAYMENT is not valid.", exception2.getMessage());
        assertEquals("The value IDIDI Dale 5000 5 ExtraParameter provided for PAYMENT is not valid.", exception3.getMessage());
    }
}
