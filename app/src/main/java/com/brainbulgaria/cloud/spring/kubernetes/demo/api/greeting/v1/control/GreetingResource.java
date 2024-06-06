package com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.control;

//import com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary.IPeopleService;
import com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary.PeopleRepository;
import com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.entity.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting/v1")
@Tag(name = "Greeting API V1", description = "API for greetings")
public class GreetingResource {

    private PeopleRepository iPeopleService;
    @Autowired
    public GreetingResource(PeopleRepository iPeopleService){
        this.iPeopleService = iPeopleService;
    }

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


    @PostMapping(path = "/hello")
    public void addName(@RequestParam(name = "Person", required = false) Person person) {
        iPeopleService.save(person);
    }

}
