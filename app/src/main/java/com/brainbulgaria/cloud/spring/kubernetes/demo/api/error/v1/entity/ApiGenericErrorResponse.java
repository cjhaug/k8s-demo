package com.brainbulgaria.cloud.spring.kubernetes.demo.api.error.v1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class ApiGenericErrorResponse {

    protected int statusCode;
    protected String requestUrl;
    protected Date requestTimestamp;

    /**
     * Default constructor.
     */
    protected ApiGenericErrorResponse() {
        super();
    }

    /**
     * The status code of the HTTP response message.
     *
     * @return the HTTP status code
     */
    @Schema(description = "HTTP status code", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Set status code.
     *
     * @param statusCode status code
     */
    protected void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * The url of the request.
     *
     * @return url used in the request
     */
    @Schema(description = "url of the request", example = "https://test.server.com/service/status/v1/health", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * Set the url of the request.
     *
     * @param requestUrl url used in the request
     */
    public void setRequestUrl(final String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * The timestamp.
     *
     * @return the time the error occurred
     */
    @Schema(description = "Timestamp when the error occurred", example = "1496132235000", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", with = JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
    public Date getRequestTimestamp() {
        return new Date(requestTimestamp.getTime());
    }

    /**
     * Set the timestamp.
     *
     * @param requestTimestamp the time the error occurred
     */
    public void setRequestTimestamp(final Date requestTimestamp) {
        this.requestTimestamp = new Date(requestTimestamp.getTime());
    }

}
