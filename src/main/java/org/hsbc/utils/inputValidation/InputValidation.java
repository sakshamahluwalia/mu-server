package org.hsbc.utils.inputValidation;

import java.util.regex.Pattern;

public class InputValidation {

    public static boolean isCurrencyValid(String currency) {
        return currency != null && currency.length() == 3;
    }

    // I am not sure if this is the best way to check for currency safety
    // adding this here for now
    public static boolean isCurrencySafe(String currency) {
        // Add code to check currency against common HTTP attacks
        String attackPattern = "(?i).*<script>.*";
        return !Pattern.matches(attackPattern, currency);
    }

    public static boolean isAmountValid(String amount) {
        try {
            var parsedAmount = Double.parseDouble(amount);
            if (parsedAmount == 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
