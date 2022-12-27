package com.geektrust;

import com.geektrust.enums.LoanEnum;
import com.geektrust.exception.InvalidInputException;
import com.geektrust.utils.StringSpaceSeparated;

public class Loan {
    public String BankName;
    public String BorrowerName;
    public int Principal;
    public int NoOfYears;
    public int RateOfInterest;
    private StringSpaceSeparated stringSpaceSeparated;

    public Loan(String inputValue) throws InvalidInputException {
        ValidateInput(inputValue);
        String[] spaceSeparatedValues = stringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        BankName = spaceSeparatedValues[LoanEnum.valueOf(Constants.BankName).ordinal()];
        BorrowerName = spaceSeparatedValues[LoanEnum.valueOf(Constants.BorrowerName).ordinal()];
        Principal = Integer.parseInt(spaceSeparatedValues[LoanEnum.valueOf(Constants.Principal).ordinal()]);
        NoOfYears = Integer.parseInt(spaceSeparatedValues[LoanEnum.valueOf(Constants.NoOfYears).ordinal()]);
        RateOfInterest = Integer.parseInt(spaceSeparatedValues[LoanEnum.valueOf(Constants.RateOfInterest).ordinal()]);
    }

    private void ValidateInput(String inputValue) throws InvalidInputException {
        String[] spaceSeparatedValues = StringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        if (spaceSeparatedValues.length != LoanEnum.values().length
                || !isNumeric(spaceSeparatedValues[LoanEnum.valueOf(Constants.Principal).ordinal()])
                || !isNumeric(spaceSeparatedValues[LoanEnum.valueOf(Constants.NoOfYears).ordinal()])
                || !isNumeric(spaceSeparatedValues[LoanEnum.valueOf(Constants.RateOfInterest).ordinal()]))
        {
            throw new InvalidInputException(Constants.Loan, inputValue);
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
