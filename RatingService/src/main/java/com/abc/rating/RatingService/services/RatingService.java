package com.abc.rating.RatingService.services;

import com.abc.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //getAllRatings
    List<Rating> getRating();

    //get all by userid Ratings
    List<Rating> getRatingByUserId(String userId);

    //get all by Hotel
    List<Rating> getRatingByHotelIds(String hotelId);

}
