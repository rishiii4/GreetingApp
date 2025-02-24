package com.greetingapp.greetingcontroller;


import com.greetingapp.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // GET request
    @GetMapping
    public Map<String, String> getGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, World! (GET)");
        return response;
    }

    // POST request
    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + request.get("name") + "! (POST)");
        return response;
    }

    // PUT request
    @PutMapping("/{id}")
    public Map<String, String> putGreeting(@PathVariable String id, @RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Updated greeting with ID: " + id + " to: " + request.get("name") + " (PUT)");
        return response;
    }

    // DELETE request
    @DeleteMapping("/{id}")
    public Map<String, String> deleteGreeting(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted greeting with ID: " + id + " (DELETE)");
        return response;
    }

    // Extend GreetingController to use Services Layer
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/simple")
    public Map<String, String> getSimpleService() {
        String message = greetingService.getSimpleGreeting();
        return Map.of("message", message);
    }

    // Greeting message with Name
    @GetMapping("/name")
    public Map<String, String> getGreetByNameService(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        String msg = greetingService.getGreetingByName(firstName, lastName);
        return Map.of("message", msg);
    }
}

