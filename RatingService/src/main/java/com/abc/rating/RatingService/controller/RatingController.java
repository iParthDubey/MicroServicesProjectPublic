package com.abc.rating.RatingService.controller;

import com.abc.rating.RatingService.entities.Rating;
import com.abc.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return  ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public  ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingService.getRating());
    }

    @GetMapping("/users/{userId}")
    public  ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable  String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public  ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable  String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelIds(hotelId));
    }
}
