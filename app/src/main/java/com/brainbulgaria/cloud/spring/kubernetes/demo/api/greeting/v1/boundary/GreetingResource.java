package com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting/v1")
public class GreetingResource {

    @GetMapping(path = "/hello")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello World!");
    }

}
