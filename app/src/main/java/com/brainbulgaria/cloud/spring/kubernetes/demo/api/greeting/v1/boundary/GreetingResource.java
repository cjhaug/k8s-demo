package com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting/v1")
@Tag(name = "Greeting API V1", description = "API for greetings")
public class GreetingResource {

    @GetMapping(path = "/hello")
    @Operation(summary = "Say Hello",
            description = "Say hello with given optional name or world if not defined",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
            })
    public ResponseEntity<String> greet(@RequestParam(name = "name", required = false) String name) {
        final String response = String.format("Hello %s!", name != null ? name : "world");
        return ResponseEntity.ok(response);
    }

}
