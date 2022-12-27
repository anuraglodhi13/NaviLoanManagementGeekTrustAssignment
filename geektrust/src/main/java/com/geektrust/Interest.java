package com.geektrust;

public class Interest {
    private  int _rate;
    private  int _noOfYears;
    private  int _principleAmount;

    public Interest(int rate, int noOfYears, int principleAmount)
    {
        _rate = rate;
        _noOfYears = noOfYears;
        _principleAmount = principleAmount;
    }

    public int GetInterest()
    {
        return  (int)Math.ceil((double) (((_rate * _principleAmount) / 100 ) * _noOfYears));
    }
}
