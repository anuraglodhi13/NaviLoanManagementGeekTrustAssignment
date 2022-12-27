package com.geektrust;

import com.geektrust.enums.BalanceEnum;
import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoBorrowerException;
import com.geektrust.utils.StringSpaceSeparated;

import java.util.List;

public class Balance {
    private String _bankName;
    private String _borrowerName;
    private int _emiNo;

    public Balance(String inputValue) throws InvalidInputException {
        validateInput(inputValue);
        String[] spaceSeparatedValues = StringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        _bankName = spaceSeparatedValues[BalanceEnum.valueOf(Constants.BankName).ordinal()];
        _borrowerName = spaceSeparatedValues[BalanceEnum.valueOf(Constants.BorrowerName).ordinal()];
        _emiNo = Integer.parseInt(spaceSeparatedValues[BalanceEnum.valueOf(Constants.EmiNo).ordinal()]);
    }

    public String getBalanceWithRemainingInstallments(List <Loan> loans, List <Payment> payments) throws NoBorrowerException {
        Loan loanOnBorrowerName = loanOnBorrowerName(loans);
        EquatedMonthlyInstallment equatedMonthlyInstallment = new EquatedMonthlyInstallment(loanOnBorrowerName.Principal, loanOnBorrowerName.RateOfInterest, loanOnBorrowerName.NoOfYears);
        int monthlyEmiInstallment = equatedMonthlyInstallment.GetEquatedMonthlyInstallment();
        Integer lumpSumAmountPaid = null;
        for (Payment i : payments) {
            if(hasBorrowerPaidLumpSumAmount(i)) {
                lumpSumAmountPaid = lumpSumAmountPaidBeforeEmi(payments);
                break;
            }
        }
        if(lumpSumAmountPaid == null) {
            lumpSumAmountPaid = Constants.NO_ADVANCED_PAYMENT;
        }
        int calculatedBalance = getBalance(monthlyEmiInstallment, lumpSumAmountPaid);
        int totalAmountToPay = equatedMonthlyInstallment.GetTotalAmountToPay();
        int totalBalance = calculatedBalance > totalAmountToPay ? totalAmountToPay : calculatedBalance;
        int remainingInstallments = getRemainingInstallments(loanOnBorrowerName, monthlyEmiInstallment, lumpSumAmountPaid);

        if (remainingInstallments * monthlyEmiInstallment + totalBalance < totalAmountToPay)
        {
            remainingInstallments++;
        }

        return _bankName + " " + _borrowerName + " " + totalBalance + " " + remainingInstallments;
    }

    private Loan loanOnBorrowerName(List <Loan> loans) throws NoBorrowerException {
        Loan loanOnBorrowerName = null;
        int numberOfTimesBorrowerTookLoan = 0;
            for(Loan i : loans) {
                if(hasBorrowerTookLoan(i)) {
                    loanOnBorrowerName = i;
                    numberOfTimesBorrowerTookLoan++;
                }
            }
            if(loanOnBorrowerName == null || numberOfTimesBorrowerTookLoan > 1) {
                throw new NoBorrowerException(_borrowerName, _bankName);
            }
        return loanOnBorrowerName;
    }

    private int getRemainingInstallments(Loan loan, int monthlyEmiInstallment, int lumpsumAmountPaid)
    {
        float monthlyInstallment = monthlyEmiInstallment;
        int lumpSumPaidForMonths = Math.round(lumpsumAmountPaid / monthlyInstallment);
        return (loan.NoOfYears * Constants.MonthsInSingleYear - _emiNo) - lumpSumPaidForMonths;
    }

    private int getBalance(int monthlyEmiInstallment, int lumpsumAmountPaid)
    {
        return (monthlyEmiInstallment * _emiNo) + lumpsumAmountPaid;
    }

    private int lumpSumAmountPaidBeforeEmi(List <Payment> payments)
    {
        int sum = 0;
        for(Payment i : payments) {
            if(hasBorrowerPaidLumpSumAmount(i)) {
                if(i.EmiNo <= _emiNo) {
                    sum+=i.LumpsumAmount;
                }
            }
        }
        return sum;
    }

    private Boolean hasBorrowerTookLoan(Loan loan)
    {
        return loan.BorrowerName.equals(_borrowerName) &&
                loan.BankName.equals(_bankName);
    }

    private Boolean hasBorrowerPaidLumpSumAmount(Payment payment)
    {
        return payment.BorrowerName.equals(_borrowerName) &&
                payment.BankName.equals(_bankName);
    }

    private void validateInput(String inputValue) throws InvalidInputException {
        String[] spaceSeparatedValues = StringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        if (spaceSeparatedValues.length != BalanceEnum.values().length ||
                !isNumeric(spaceSeparatedValues[BalanceEnum.valueOf(Constants.EmiNo).ordinal()]))
        {
            throw new InvalidInputException(Constants.Balance, inputValue);
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
