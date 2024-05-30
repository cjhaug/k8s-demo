package com.brainbulgaria.cloud.spring.kubernetes.demo.api.error.v1.boundary;

import com.brainbulgaria.cloud.spring.kubernetes.demo.api.error.v1.entity.ApiClientErrorResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The default spring whitelabel is disabled in application.yml so following resource is provided to handle errors instead.
 * Builds {@link ApiClientErrorResponse}
 */
@RestController
public class ErrorResource implements ErrorController {

    /**
     * Error endpoint resource to handle errors like 401, 403 or 404 for GET requests.
     *
     * @param httpServletRequest http request object
     * @param httpServletResponse http response object
     */
    @GetMapping(path = "/error")
    public ResponseEntity<ApiClientErrorResponse> handleErrorForGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        return handleError(httpServletRequest, httpServletResponse);
    }

    /**
     * Error endpoint resource to handle errors like 401, 403 or 404 for state changing HTTP requests.
     *
     * @param httpServletRequest http request object
     * @param httpServletResponse http response object
     */
    @RequestMapping(path = "/error", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<ApiClientErrorResponse> handleErrorForStateChangingMethods(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        return handleError(httpServletRequest, httpServletResponse);
    }

    private ResponseEntity<ApiClientErrorResponse> handleError(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        String url = null;
        final int httpStatusCode = httpServletResponse.getStatus();

        if (httpServletRequest != null) {
            final Object requestUri = httpServletRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
            if (requestUri != null) {
                url = requestUri.toString();
            }
        }

        final ApiClientErrorResponse response = ApiClientErrorResponse.create(httpStatusCode, url);
        return ResponseEntity.status(HttpStatus.valueOf(httpStatusCode)).body(response);
    }
}
