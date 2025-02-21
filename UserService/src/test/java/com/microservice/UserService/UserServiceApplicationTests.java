package com.microservice.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.microservice.UserService.entity.Rating;
import com.microservice.UserService.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;
    private ResponseEntity<Rating> updateRating;

	@Test
	void createRating(){
		Rating rating = Rating.builder().userId("")
		.hotelId("9e4482ce-00f4-4e28-85c3-e6974e56694c").rating(10)
		.feedback("testing rating").build();

		ResponseEntity<Rating> savedRating = ratingService.createRating(rating);
		System.out.println("new Rating created....");
		System.out.println(savedRating.getStatusCode());
	}

	@Test
	void updateRating(){
		String ratingId="67629989d9209a53a3e2d2ed";
		Rating rating = Rating.builder().userId("")
		.hotelId("").rating(100)
		.feedback("testing testing update rating").build();

		ResponseEntity<Rating>updateRating = ratingService.updateRating(ratingId, rating);
		System.out.println("udpated rating ..........");
	}

}
