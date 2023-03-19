package com.abc.user.service.services.impl;

import com.abc.user.service.entities.Hotel;
import com.abc.user.service.entities.Rating;
import com.abc.user.service.entities.User;
import com.abc.user.service.exceptions.ResourceNotFoundException;
import com.abc.user.service.externalServices.HotelService;
import com.abc.user.service.repositories.UserRepository;
import com.abc.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server"+userId));
        // fetch Ratings of above user form Rating Service
       // http://localhost:8083/ratings/users/c4e84b73-148a-42f5-ad46-a8b70391ff63
        Rating[] ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);
        List<Rating> ratingList=Arrays.stream(ratingOfUser).toList();
        ratingList.stream().map(rating -> {
//           ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
//           logger.info("response status code: {} ",forEntity.getStatusCode());
           rating.setHotel(hotel);
           return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
