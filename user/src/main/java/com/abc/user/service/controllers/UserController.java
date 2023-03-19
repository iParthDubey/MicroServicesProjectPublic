package com.abc.user.service.controllers;

import com.abc.user.service.entities.User;
import com.abc.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    int retryCount=1;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1= userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry Count: {}",retryCount);
        retryCount++;
        User user= userService.getUser(userId);
        return  ResponseEntity.ok(user);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return  ResponseEntity.ok(allUser);
    }

    //Creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        logger.info("Fallback is executed because service is down",ex.getMessage());
        User user= User.builder()
                    .email("dummyEmail@gmail.com")
                    .name("dummy")
                    .about("This user is created because some service is down")
                    .userId("12345")
                    .build();
        return  new ResponseEntity<>(user,HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }
}
