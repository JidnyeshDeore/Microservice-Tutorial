package com.microservice.HotelService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;

}
