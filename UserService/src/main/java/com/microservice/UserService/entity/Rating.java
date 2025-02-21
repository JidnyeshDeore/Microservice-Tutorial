package com.microservice.UserService.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {

    
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
    private Hotel hotel;
}
