package org.hsbc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A singleton class to store and record payments.
 */
public class Payment {

    private static final Payment instance = new Payment();
    private final Map<String, Double> payments = new HashMap<>();

    private Double MAX_AMOUNT = 50000.0;
    private Double MIN_AMOUNT = 0.0;

    public static Payment getInstance() {
        return instance;
    }

    public void recordPayment(String currency, Double amount) {

        if (payments.containsKey(currency)) {
            var trackedAmount = payments.get(currency);

            if (Double.sum(trackedAmount, amount) > MAX_AMOUNT) {
                throw new IllegalArgumentException("tracked sum exceeds the maximum limit");
            } else if (Double.sum(trackedAmount, amount) < MIN_AMOUNT) {
                throw new IllegalArgumentException("tracked sum is below the minimum limit");
            } else {
                payments.put(currency, Double.sum(trackedAmount, amount));
            }

        } else {

            if (amount > MAX_AMOUNT) {
                throw new IllegalArgumentException("tracked sum exceeds the maximum limit");
            } else if (amount < MIN_AMOUNT) {
                throw new IllegalArgumentException("initial amount cannot less than minimum limit");
            } else {
                payments.put(currency, amount);
            }

        }
        return;
    }

    public Map<String, Double> getPayments() {
        return new HashMap<>(payments);
    }
}
