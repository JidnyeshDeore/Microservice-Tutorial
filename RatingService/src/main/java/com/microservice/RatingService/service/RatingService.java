package com.microservice.RatingService.service;

import java.util.List;

import com.microservice.RatingService.entity.Rating;

public interface RatingService {



    public Rating saveRating(Rating rating);

    public Rating getRatingById(String ratingId);

    public List<Rating>getAllRatings();

    public List<Rating> getRatingByUserId(String userId);

    public List<Rating> getRatingByHotelId(String hotelId);

    public Rating updateRating(String ratingId, Rating rating);
}
