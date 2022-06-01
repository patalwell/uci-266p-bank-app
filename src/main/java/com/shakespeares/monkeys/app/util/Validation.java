package com.shakespeares.monkeys.app.util;

import com.sun.istack.NotNull;


import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validation {

    private static final String nameRegex = "[a-zA-Z]{1,56}";
    private static final String credentialsRegex = "[_\\-.a-z0-9]{1,127}";
    private static final String balanceRegex = "(0|[1-9][0-9]*(\\.[0-9]{2})?)";

    public static Boolean validateFirstLastName(@NotNull String name){
        if (name.length() > 0 && name.length()<=56) {
            Pattern pattern = Pattern.compile(nameRegex);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        } else {
            return false;
        }
    }
    
    public static Boolean validateUserNameCredentials(@NotNull String credential) {
        if (credential.length()>0 && credential.length()<=127) {
            Pattern pattern = Pattern.compile(credentialsRegex);
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
                Pattern pattern = Pattern.compile(balanceRegex);
                Matcher matcher = pattern.matcher(numberInput);
                return matcher.matches();
            }
            else{
                return false;
            }
    }
}
