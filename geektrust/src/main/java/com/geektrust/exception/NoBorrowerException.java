package com.geektrust.exception;

public class NoBorrowerException extends Exception{
    public NoBorrowerException(String borrowerName, String bankName) {
        super("Either the borrower " + borrowerName + " hasn't took the loan from bank " + bankName +
                " or has took more than one loan.");
    }
}
