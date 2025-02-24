package com.greetingapp.services;

import com.greetingapp.model.Greeting;
import com.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

