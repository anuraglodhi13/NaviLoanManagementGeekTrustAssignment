package com.geektrust.exception;

public class InvalidInputException extends Exception{
    public InvalidInputException(String type, String value) {
        super("The value " + value + " provided for " + type + " is not valid.");
    }
}
