package com.microservice.HotelService.service;

import java.util.List;

import com.microservice.HotelService.entity.Hotel;

public interface HotelService {

    public Hotel addHotel(Hotel hotel);

    public Hotel getHotel(String hotelId);

    public List<Hotel>getAllHotels();
}
