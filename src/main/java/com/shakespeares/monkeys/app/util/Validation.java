package com.shakespeares.monkeys.app.util;

import com.sun.istack.NotNull;


import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validation {

    private static final String credentialsFormat = "[_\\-.a-z0-9]+";
    private static final String depositWithdrawAmount = "(0|[1-9][0-9]*(\\.[0-9]{2})?)";
    
    public static Boolean validateCredentials(@NotNull String credential) {
        if (credential.length()>0 && credential.length()<=127) {
            Pattern pattern = Pattern.compile(credentialsFormat);
            Matcher matcher = pattern.matcher(credential);
            return matcher.matches();
        }
        else{
            return false;
        }
    }

    public static Boolean validateNumericInput(@NotNull BigDecimal amount) {
            if(amount.doubleValue() >= 0.00 && amount.doubleValue() <= 4294967295.99) {
                String numberInput = amount.toString();
                Pattern pattern = Pattern.compile(depositWithdrawAmount);
                Matcher matcher = pattern.matcher(numberInput);
                return matcher.matches();
            }
            else{
                return false;
            }
    }
}
