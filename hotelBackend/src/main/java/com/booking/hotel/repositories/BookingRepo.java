package com.booking.hotel.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.booking.hotel.models.Booking;

public interface BookingRepo extends CrudRepository<Booking, Integer> {

	@Query(value = "SELECT sum(rooms) as count from booking where hotel_id=:id and :checkinDate  BETWEEN check_in and check_out")
	public GetTotalSum getAvailableRoomCount(@Param(value ="id")int hotelId,
			@Param(value ="checkinDate") Date checkInDate);
}
