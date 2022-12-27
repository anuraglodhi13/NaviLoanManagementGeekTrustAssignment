package com.geektrust;

import com.geektrust.enums.PaymentEnum;
import com.geektrust.exception.InvalidInputException;
import com.geektrust.utils.StringSpaceSeparated;

public class Payment {
    public String BankName;
    public String BorrowerName;
    public int LumpsumAmount;
    public int EmiNo;
    public Payment(String inputValue) throws InvalidInputException {
        validateInput(inputValue);
        String[] spaceSeparatedValues = StringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        BankName = spaceSeparatedValues[PaymentEnum.valueOf(Constants.BankName).ordinal()];
        BorrowerName = spaceSeparatedValues[PaymentEnum.valueOf(Constants.BorrowerName).ordinal()];
        LumpsumAmount = Integer.parseInt(spaceSeparatedValues[PaymentEnum.valueOf(Constants.LumpsumAmount).ordinal()]);
        EmiNo = Integer.parseInt(spaceSeparatedValues[PaymentEnum.valueOf(Constants.EmiNo).ordinal()]);
    }

    private void validateInput(String inputValue) throws InvalidInputException {
        String[] spaceSeparatedValues = StringSpaceSeparated.getSpaceSeparatedValues(inputValue);
        if (spaceSeparatedValues.length != PaymentEnum.values().length ||
                !isNumeric(spaceSeparatedValues[PaymentEnum.valueOf(Constants.LumpsumAmount).ordinal()]) ||
                !isNumeric(spaceSeparatedValues[PaymentEnum.valueOf(Constants.EmiNo).ordinal()]))
        {
            throw new InvalidInputException(Constants.Payment, inputValue);
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
