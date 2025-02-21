package com.microservice.RatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.RatingService.entity.Rating;
import com.microservice.RatingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating newRating = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRating);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingById(ratingId));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> allRatings = ratingService.getAllRatings();
        return  ResponseEntity.ok(allRatings);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable String userId) {
        List<Rating> allRatings = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(allRatings, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable String hotelId) {
        List<Rating> allRatings = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(allRatings, HttpStatus.OK);
    }


    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating>updateRating(@PathVariable("ratingId")String ratingId,@RequestBody Rating rating){

        Rating newRating=ratingService.updateRating(ratingId,rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRating);
    }
}
