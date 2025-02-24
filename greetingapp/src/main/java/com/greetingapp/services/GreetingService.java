package com.greetingapp.services;

import org.springframework.stereotype.Service;


@Service
public class GreetingService {

    public String getSimpleGreeting() {
        return "Hello Spring Boot greeting";
    }

    public String getGreetingByName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, "+ firstName + " " + lastName;
        }
        if (firstName != null) {
            return "Hello, "+ firstName;
        }
        if (lastName != null) {
            return "Hello, "+ lastName;
        }
        return "Hello, Sir";
    }
}

