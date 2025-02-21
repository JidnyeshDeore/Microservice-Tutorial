package com.microservice.HotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.HotelService.entity.Hotel;
import com.microservice.HotelService.exception.HotelNotFoundException;
import com.microservice.HotelService.repository.HotelRepository;


@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel addHotel(Hotel hotel) {

        String id=UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        
        return hotelRepository.findById(hotelId)
            .orElseThrow(()-> new HotelNotFoundException("Hotel Not found with id: "+hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
       
        return hotelRepository.findAll();
    }

}
