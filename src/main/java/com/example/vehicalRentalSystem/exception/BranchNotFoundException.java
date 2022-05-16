package com.example.vehicalRentalSystem.exception;

import java.text.MessageFormat;

public class BranchNotFoundException extends RuntimeException{
    public BranchNotFoundException(String msg) {
        super(msg);
    }
}
