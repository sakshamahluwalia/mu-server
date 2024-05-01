package org.hsbc.utils.logging;

import java.util.Timer;
import java.util.TimerTask;

import org.hsbc.service.paymentService.PaymentService;

public class CustomLogger {

    Timer timer = new Timer();
    private PaymentService paymentService = new PaymentService();

    private int TIME_LIMIT = 10 * 1000;

    public void startLoggingPayments() {
        System.out.println("starting to log payments...");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                paymentService.printPayments();
            }
        }, 0, TIME_LIMIT);
    }
}
