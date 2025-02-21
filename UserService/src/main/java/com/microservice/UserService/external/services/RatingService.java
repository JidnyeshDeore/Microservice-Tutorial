package com.microservice.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.microservice.UserService.entity.Rating;


@Service
@FeignClient(name = "RatingService")
public interface RatingService {

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating>getRatingById(@PathVariable("ratingId")String ratingId);

    @PostMapping("/ratings")
    public ResponseEntity<Rating>createRating(Rating rating);

    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating>updateRating(@PathVariable("ratingId")String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating>deleteRating(@PathVariable("ratingId")String ratingId);

}
