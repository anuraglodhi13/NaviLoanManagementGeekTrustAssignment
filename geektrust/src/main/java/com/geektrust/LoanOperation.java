package com.geektrust;

import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoBorrowerException;

import java.util.ArrayList;
import java.util.List;
public class LoanOperation {
    private final ArrayList <Loan> _loans;
    private final ArrayList<Payment> _payments;
    private final ArrayList<String> _balanceList;

    public LoanOperation()
    {
         _loans = new ArrayList<>();
        _payments = new ArrayList<>();
        _balanceList = new ArrayList<>();
    }

    public void performOperation(String lineFromFile) throws Exception {
        switch (lineFromFile.split(Constants.Space)[0])
        {
            case Constants.Loan:
                loanOperation(lineFromFile);
                break;
            case Constants.Payment:
                paymentOperation(lineFromFile);
                break;
            case Constants.Balance:
                balanceOperation(lineFromFile);
                break;
            default:
                throw new Exception("Invalid line supplied in file");
        }
    }

    public List<String> getBalances()
    {
        return _balanceList;
    }

    private void loanOperation(String lineFromFile) throws InvalidInputException {
        Loan loan = new Loan(lineFromFile.substring(Constants.Loan.length() + 1));
        _loans.add(loan);
    }

    private void paymentOperation(String lineFromFile) throws InvalidInputException {
        Payment payment = new Payment(lineFromFile.substring(Constants.Payment.length() + 1));
        _payments.add(payment);
    }

    private void balanceOperation(String lineFromFile) throws NoBorrowerException, InvalidInputException {
        Balance balance = new Balance(lineFromFile.substring(Constants.Balance.length() + 1));
        String balanceWithRemainingInstallments = balance.getBalanceWithRemainingInstallments(_loans, _payments);
        _balanceList.add(balanceWithRemainingInstallments);
    }
}
