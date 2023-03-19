package com.abc.rating.RatingService.repositories;

import com.abc.rating.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRespository extends MongoRepository<Rating, String> {

    //custom finder methods
    List<Rating> findByUserId(String userid);
    List<Rating> findByHotelId(String hotelId);
}
