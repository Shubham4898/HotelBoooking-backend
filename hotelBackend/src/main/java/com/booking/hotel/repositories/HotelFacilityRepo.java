package com.booking.hotel.repositories;

import org.springframework.data.repository.CrudRepository;

import com.booking.hotel.models.HotelFacility;

public interface HotelFacilityRepo extends CrudRepository<HotelFacility, Integer> {

}
