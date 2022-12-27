package com.geektrust;

public class EquatedMonthlyInstallment {
    private int _principleAmount;
    private int _noOfYears;
    private Interest _interest;

    public EquatedMonthlyInstallment(int principleAmount, int rate, int noOfYears)
    {
        _principleAmount = principleAmount;
        _noOfYears = noOfYears;
        _interest = new Interest(rate, noOfYears, principleAmount);
    }

    public int GetEquatedMonthlyInstallment()
    {
        int calculatedInterest = _interest.GetInterest();
        float noOfMonths = _noOfYears * Constants.MonthsInSingleYear;
        return (int) Math.ceil((_principleAmount + calculatedInterest) / noOfMonths);
    }

    public int GetTotalAmountToPay()
    {
        return _interest.GetInterest() + _principleAmount;
    }
}
