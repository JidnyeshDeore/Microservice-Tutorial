package com.microservice.UserService.service;

import java.util.Arrays;
import java.util.List;

import java.util.UUID;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.UserService.entity.Hotel;
import com.microservice.UserService.entity.Rating;
import com.microservice.UserService.entity.User;
import com.microservice.UserService.exception.UserNotFoundException;
import com.microservice.UserService.external.services.HotelService;
import com.microservice.UserService.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;



    @Override
    public User savUser(User user) {
        
        String id=UUID.randomUUID().toString();
        user.setId(id);
        return userRepository.save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getUser(String userId) {
        
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("USer not found: "+userId));

        //fetch rating of the above user from RATING-SERVICE 
        Rating[] ratingsOfUser= restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
  

        List<Rating>ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating>ratingList=ratings.stream().map(rating->{
            //api call to HOTEL_SERVICE to get hotel
            //1. using RestTemplate
            // ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            // Hotel hotel=forEntity.getBody();

            Hotel hotel=hotelService.getHotel(rating.getHotelId());//2.using feignCleint

            
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        
        return userRepository.findAll();
    }

}
