package com.example.mobileappbook.utils;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Validations {
    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (!matcher.matches() && email.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean isPasswordValid(String password) {
        String PASSWORD_PATTERN = "^[a-z0-9]{6,12}$";
        CharSequence inputStr = password;
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (!matcher.matches() && password.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean isValidPhoneNumber(String number) {
        String validNumber = "^0[35789]{1}\\d{8}$";
        Pattern pattern = Pattern.compile(validNumber, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);
        String validNumber1 = "^(([+84]{3})|[84]{2})[35789]{1}\\d{8}$";
        Pattern pattern1 = Pattern.compile(validNumber1, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(number);
        String validNumber3 = "^[35789]{1}\\d{8}$";
        Pattern pattern3 = Pattern.compile(validNumber3, Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(number);
        if (matcher.find() || matcher1.find() || matcher3.find()) {
            return true;
        }
        return false;
    }

    public static boolean isValidName(String s) {
        String validName = "[0-9]*";
        if (s.toString().matches(validName) || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidSpecialCharacters(Editable s) {
        Pattern regex = Pattern.compile("[$&+,:;=\\?@#|/'<>.^*()%!-]");//~`•√ππ÷×¶∆\}{°¢€£©®™✓
        if (regex.matcher(s).find() ) {
            return true;
        }
        return false;
    }

    public static boolean isValidAddress(String s) {
        Pattern regex = Pattern.compile("[$&+:;=\\?@#|/'<>.^*()%!]");//~`•√ππ÷×¶∆\}{°¢€£©®™✓
        if (regex.matcher(s).find() || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String replaceMultiple(String baseString, String... replaceParts) {
        for (String s : replaceParts) {
            baseString = baseString.replaceAll(s, "");
        }
        return baseString;
    }

    public static void hideKeyboard(View v, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }
}

