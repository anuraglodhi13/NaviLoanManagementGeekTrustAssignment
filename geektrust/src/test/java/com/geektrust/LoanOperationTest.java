package com.geektrust;

import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoanOperationTest {
    @Test
    public void GivenSampleDataOfLoan_WhenCheckedBalanceAfterPerformingLoanOperation_ShouldHaveCorrectValues() throws Exception {
        // Given
        LoanOperation loanOperation = new LoanOperation();
        List<String> sampleData = getSampleLoanDataFromAssignment();
        for(String i :sampleData) {
            loanOperation.performOperation(i);
        }

        // When
        String balances = toCommaSeparatedString(loanOperation.getBalances());

        // Then
        assertEquals("IDIDI Dale 1326 9, IDIDI Dale 3652 4, UON Shelly 15856 3, MBI Harry 9044 10", balances);
    }
    private static List<String> getSampleLoanDataFromAssignment()
    {
        List<String> sampleLoanDataList = Arrays.asList(
                "LOAN IDIDI Dale 5000 1 6",
                "LOAN MBI Harry 10000 3 7",
                "LOAN UON Shelly 15000 2 9",
                "PAYMENT IDIDI Dale 1000 5",
                "PAYMENT MBI Harry 5000 10",
                "PAYMENT UON Shelly 7000 12",
                "BALANCE IDIDI Dale 3",
                "BALANCE IDIDI Dale 6",
                "BALANCE UON Shelly 12",
                "BALANCE MBI Harry 12");
        return sampleLoanDataList;
    }
    private static String toCommaSeparatedString(List<String> values)
    {
        return String.join(", ", values);
    }
}
