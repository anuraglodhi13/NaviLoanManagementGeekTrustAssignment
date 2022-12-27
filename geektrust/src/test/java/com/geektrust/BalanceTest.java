package com.geektrust;

import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoBorrowerException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class BalanceTest {
    @Test
    public void GivenValidLoan_WhenGetBalanceWithRemainingInstallments_ShouldReturnCorrectValue() throws InvalidInputException, NoBorrowerException {
        // Given
        String inputText = "IDIDI Dale 5";
        List <Loan> loans = new ArrayList<>();
        Loan loan = new Loan ("IDIDI Dale 10000 5 4");
        loans.add(loan);

        // When
        Balance balance = new Balance(inputText);
        String balanceWithRemainingInstallments = balance.getBalanceWithRemainingInstallments(loans, new ArrayList<Payment>());

        // Then
        assertEquals("IDIDI Dale 1000 55", balanceWithRemainingInstallments);
    }

    @Test
    public void GivenValidLoanAndPayment_WhenGetBalanceWithRemainingInstallments_ShouldReturnCorrectValue() throws InvalidInputException, NoBorrowerException {
        // Given
        String inputText = "IDIDI Dale 6";
        List <Loan> loans = new ArrayList<>();
        List <Payment> payments = new ArrayList<>();
        Loan loan = new Loan ("IDIDI Dale 5000 1 6");
        Payment payment = new Payment ("IDIDI Dale 1000 5");
        loans.add(loan);
        payments.add(payment);

        // When
        Balance balance = new Balance(inputText);
        String balanceWithRemainingInstallments = balance.getBalanceWithRemainingInstallments(loans, payments);

        // Then
        assertEquals("IDIDI Dale 3652 4", balanceWithRemainingInstallments);
    }

    @Test
    public void GivenLoanDoesNotExists_WhenGetBalanceWithRemainingInstallments_ShouldThrowExcpetion() throws InvalidInputException, NoBorrowerException {
        // Given
        String inputText = "IDIDI Dale 6";
        List <Loan> loans = new ArrayList<>();
        Loan loan = new Loan ("IDIDI NotDale 5000 1 6");
        loans.add(loan);

        // When
        Balance balance = new Balance(inputText);
        Exception exception = assertThrows(NoBorrowerException.class, () -> {
            balance.getBalanceWithRemainingInstallments(loans, new ArrayList<Payment>());
        });

        // Then
        assertEquals("Either the borrower Dale hasn't took the loan from bank IDIDI or has took more than one loan.", exception.getMessage());
    }

    @Test
    public void GivenInvalidValues_WhenBalanceObjectCreated_ShouldThrowInvalidInputException() {
        // Given
        String inputText1 = "IDIDI MissingEmi";
        String inputText2 = "IDIDI Dale 5 ExtraValue";
        String inputText3 = "IDIDI Dale InvalidEmi";

        // When

        Exception exception1 = assertThrows(InvalidInputException.class, () -> {
            Balance balance1 = new Balance(inputText1);
        });
        Exception exception2 = assertThrows(InvalidInputException.class, () -> {
            Balance balance2 = new Balance(inputText2);
        });
        Exception exception3 = assertThrows(InvalidInputException.class, () -> {
            Balance balance3 = new Balance(inputText3);
        });

        // Then
        assertEquals("The value IDIDI MissingEmi provided for BALANCE is not valid.", exception1.getMessage());
        assertEquals("The value IDIDI Dale 5 ExtraValue provided for BALANCE is not valid.", exception2.getMessage());
        assertEquals("The value IDIDI Dale InvalidEmi provided for BALANCE is not valid.", exception3.getMessage());

    }

}
