package org.hsbc.controller;

import io.muserver.MuRequest;
import io.muserver.MuResponse;

import java.util.Map;

import org.hsbc.service.paymentService.PaymentService;
import org.hsbc.utils.inputValidation.InputValidation;
import org.hsbc.service.httpResponseService.HttpResponseService;

/**
 * A controller class to handle payment requests.
 */
public class PaymentServiceController {

    private PaymentService paymentService = new PaymentService();
    private HttpResponseService httpResponseService = new HttpResponseService();

    public void handlePayment(MuRequest request, MuResponse response, Map<String, String> pathParams) {

        var currency = pathParams.get("currencyCode");
        var amount = pathParams.get("amount");

        // Validate the input
        var isCurrencyValid = InputValidation.isCurrencyValid(currency);
        var isAmountValid = InputValidation.isAmountValid(amount);

        // If the input is invalid, return a 400 response
        if (!isCurrencyValid || !isAmountValid) {
            httpResponseService.createAndSendResponse("Invalid input - please check the inputs and try again", 400,
                    response);
            return;
        }

        // Record the payment
        var validatedAmount = Double.parseDouble(amount);
        var validatedCurrency = currency.toUpperCase();

        // If an error occurs while recording the payment, handle it gracefully
        try {
            paymentService.recordPayment(validatedCurrency, validatedAmount);
        } catch (IllegalArgumentException e) {
            httpResponseService.createAndSendResponse(e.getMessage(), 400, response);
            return;
        } catch (Exception e) {
            httpResponseService.createAndSendResponse("An error occurred while recording the payment", 500, response);
            return;
        }
        var updatedPayments = paymentService.getPaymentsWithIntValues();

        // Return the updated payments
        httpResponseService.createAndSendResponse(updatedPayments, 200, response);
    }
}
