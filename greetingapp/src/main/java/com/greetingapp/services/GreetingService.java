package com.greetingapp.services;

import com.greetingapp.model.Greeting;
import com.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    GreetingRepository greetingRepository;

    public String getSimpleGreeting() {
        return "Hello Spring Boot greeting";
    }

    public String getGreetingByName(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, "+ firstName + " " + lastName;
        }else
        if (firstName != null) {
            message = "Hello, "+ firstName;
        }else
        if (lastName != null) {
            message = "Hello, "+ lastName;
        }else{
            message = "Hello, nameless";
        }

        // Save the greeting repository
        greetingRepository.save(new Greeting(message));
        return message;


    }
    public Greeting findGreetingById(long id) {
        return greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting not found by id : "+ id));
    }

    public List<Greeting> getAllGreeting() {
        return greetingRepository.findAll();
    }

    public Greeting update(long id, String newMsg) {
        Greeting greeting = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting not found by id : "+ id));
        greeting.setMessage(newMsg);
        return greetingRepository.save(greeting);
    }

    public void deleteGreeting(long id) {
        if (!greetingRepository.existsById(id)) {
            throw new NoSuchElementException("Greeting with ID " + id + " not found.");
        }
        greetingRepository.deleteById(id);
    }

}

