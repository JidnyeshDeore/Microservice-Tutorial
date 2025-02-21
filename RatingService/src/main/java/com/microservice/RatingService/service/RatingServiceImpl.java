package com.microservice.RatingService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.RatingService.entity.Rating;
import com.microservice.RatingService.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    private Optional<Rating> byId;
    private Optional<Rating> oldRating;


    @Override
    public Rating saveRating(Rating rating) {
        
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(String ratingId) {
       
        return ratingRepository.findById(ratingId).orElse(null);
    }

    @Override
    public List<Rating> getAllRatings() {
       
        List<Rating>allRatings=ratingRepository.findAll();
        return allRatings;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        Optional<Rating> old = ratingRepository.findById(ratingId);
        if (old.isEmpty()) {
            throw new RuntimeException("Rating not found with ID: " + ratingId);
        }
    
        // Retain existing properties if not provided
        rating.setUserId(old.get().getUserId());
        rating.setHotelId(old.get().getHotelId());
        rating.setRating(old.get().getRating());
        rating.setFeedback(old.get().getFeedback());
    
        return ratingRepository.save(rating);
    }
    

}
