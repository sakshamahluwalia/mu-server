package org.hsbc;

import org.hsbc.controller.PaymentServiceController;
import org.hsbc.utils.logging.CustomLogger;

import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;

public class Server {

    private PaymentServiceController paymentServiceController = new PaymentServiceController();
    private CustomLogger customLogger = new CustomLogger();

    public void startServer() {
        MuServer server = MuServerBuilder.httpServer()
                .withHttpPort(3000)
                .addHandler(Method.GET, "/reset-transactions", paymentServiceController::handleResetTransactions)
                .addHandler(Method.POST, "/payment/{currencyCode}/{amount}", paymentServiceController::handlePayment)
                .start();

        customLogger.startLoggingPayments();
        System.out.println("Server started at: " + server.uri());
    }
}
