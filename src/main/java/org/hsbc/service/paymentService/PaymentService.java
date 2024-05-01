package org.hsbc.service.paymentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hsbc.model.Payment;

/**
 * A service class to record payments and provide a summary of payments.
 */
public class PaymentService {

    private Payment payment = Payment.getInstance();

    public void resetPayments() {
        payment.resetPayments();
    }


    public void recordPayment(String currency, Double amount) {
        payment.recordPayment(currency, amount);
    }

    public Map<String, Double> getPayments() {
        return payment.getPayments();
    }

    /**
     * Get payments as list
     * 
     * @return List of payments
     */
    public List<Entry<String, Double>> getPaymentsAsList() {
        var payments = payment.getPayments();
        return List.copyOf(payments.entrySet());
    }

    /**
     * Get payments with transformed values to int
     * 
     * @return Map of payments with entries.value transformed to int
     */
    public Map<String, Integer> getPaymentsWithIntValues() {
        var payments = payment.getPayments();
        var paymentsWithIntKeys = new HashMap<String, Integer>();
        for (Map.Entry<String, Double> entry : payments.entrySet()) {
            paymentsWithIntKeys.put(entry.getKey(), entry.getValue().intValue());
        }
        return paymentsWithIntKeys;
    }

    /**
     * Print payments to console
     */
    public void printPayments() {
        var payments = payment.getPayments();
        for (Map.Entry<String, Double> entry : payments.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().intValue());
        }
    }
}
