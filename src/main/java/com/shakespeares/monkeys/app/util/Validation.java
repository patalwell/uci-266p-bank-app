package com.shakespeares.monkeys.app.util;

import com.sun.istack.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validation {

    private static final String regex = "[_\\-.a-z0-9]+";
    
    public static String validateCredentials(@NotNull String credential) {
        if (credential.length()>0 && credential.length()<=127) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(credential);
            return matcher.matches() ? "valid" : "invalid";
        }
        else{
            System.out.println(credential.length());
            return "invalid";
        }
    }
}
