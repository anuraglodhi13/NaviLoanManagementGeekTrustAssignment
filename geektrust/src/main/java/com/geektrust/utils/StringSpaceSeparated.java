package com.geektrust.utils;
import com.geektrust.Constants;

public class StringSpaceSeparated {
    public static String[] getSpaceSeparatedValues(String inputValue) {
        return inputValue.split(Constants.Space);

    }
}
