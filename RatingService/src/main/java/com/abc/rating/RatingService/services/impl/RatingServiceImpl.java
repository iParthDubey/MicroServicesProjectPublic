package com.abc.rating.RatingService.services.impl;

import com.abc.rating.RatingService.entities.Rating;
import com.abc.rating.RatingService.repositories.RatingRespository;
import com.abc.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRespository ratingRespository;

    @Override
    public Rating create(Rating rating) {
        return ratingRespository.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return ratingRespository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRespository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelIds(String hotelId) {
        return ratingRespository.findByHotelId(hotelId);
    }
}
