package com.microservice.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.UserService.entity.User;
import com.microservice.UserService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;    

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){

        User newUser=userService.savUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }



    int retryCount=1;
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    // @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User>getUser(@PathVariable String userId){


        System.out.println("Retry Count:------------->"+retryCount);
        retryCount++;
        User user=userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fall back method for circuit breaker
    public ResponseEntity<User>ratingHotelFallback(String userId, Exception ex){
        System.out.println("Fallback method is exceuted: "+ex.getMessage());

        User user = User.builder().email("dummy@gmail.com")
            .name("Dummy Name")
            .about("One of the Service is Down..")
            .id("123456789")
            .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User>allUsers=userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
        
    }

}



