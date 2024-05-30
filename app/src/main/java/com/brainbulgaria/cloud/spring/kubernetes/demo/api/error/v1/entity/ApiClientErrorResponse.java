package com.brainbulgaria.cloud.spring.kubernetes.demo.api.error.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(name = "ApiClientErrorResponse", description = "Object holding information about client errors (HTTP code 400).")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiClientErrorResponse extends ApiGenericErrorResponse {

    /**
     * Default constructor.
     */
    public ApiClientErrorResponse() {
        super();
        statusCode = 400;
    }

    public static ApiClientErrorResponse create(final int statusCode, final String requestUrl) {
        return createApiClientResponse(statusCode, requestUrl);
    }

    private static ApiClientErrorResponse createApiClientResponse(final int statusCode, final String requestUrl) {
        final ApiClientErrorResponse response = new ApiClientErrorResponse();
        response.setStatusCode(statusCode);
        response.setRequestUrl(requestUrl);
        response.setRequestTimestamp(new Date());
        return response;
    }

}
