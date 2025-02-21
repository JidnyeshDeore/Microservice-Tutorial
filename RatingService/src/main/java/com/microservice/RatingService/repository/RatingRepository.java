package com.microservice.RatingService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.RatingService.entity.Rating;
import java.util.List;


@Repository
public interface RatingRepository extends MongoRepository<Rating,String>{


    public List<Rating> findByUserId(String userId);

    public List<Rating> findByHotelId(String hotelId);

}
