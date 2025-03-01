package com.greetingapp.greetingcontroller;


import com.greetingapp.model.Greeting;
import com.greetingapp.repository.GreetingRepository;
import com.greetingapp.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

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

    // Save the Greeting Message in the Repository
    // List all the Greeting Messages in the Repository
    @GetMapping("/all")
    public List<Greeting> getAllGreeting() {
        return greetingRepository.findAll();
    }

    // Find a Greeting Message by Id in the Repository
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreeting(@PathVariable long id) {
        try {
            Greeting greeting = greetingService.findGreetingById(id);
            return ResponseEntity.ok(greeting);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Edit a Greeting Messages in the Repository
    @PutMapping("/change/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable long id, @RequestBody Greeting updatedGreet) {
        try {
            Greeting greeting = greetingService.update(id, updatedGreet.getMessage());
            return ResponseEntity.ok(greeting);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a Greeting Messages in the Repository
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGreet(@PathVariable long id) {
        try {
            greetingService.deleteGreeting(id);
            return ResponseEntity.ok("Greeting with ID " + id + " has been deleted.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}

