package com.booking.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.booking.hotel.models.RoomInfo;

public interface RoomInfoRepo extends CrudRepository<RoomInfo, Integer> {

	@Query(value = "Select R from  room_info R where hotel_id = :id")
	public RoomInfo findByHotelId(@Param(value = "id")int hotelId);
	
}
