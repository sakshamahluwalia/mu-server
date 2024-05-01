package org.hsbc.service.httpResponseService;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.muserver.MuResponse;

/**
 * A service to create and send HTTP responses.
 */
public class HttpResponseService {

    public void createAndSendResponse(Map<String, Integer> responseBody, int statusCode, MuResponse response) {
        response.status(statusCode);
        response.write(responseBody.toString());
    }

    public void createAndSendResponse(List<Entry<String, Double>> responseBody, int statusCode, MuResponse response) {
        response.status(statusCode);
        response.write(responseBody.toString());
    }

    public void createAndSendResponse(String responseBody, int statusCode, MuResponse response) {
        response.status(statusCode);
        response.write(responseBody);
    }

}
