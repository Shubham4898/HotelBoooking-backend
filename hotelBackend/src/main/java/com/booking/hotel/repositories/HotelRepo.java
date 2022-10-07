package com.booking.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.booking.hotel.models.Hotel;

public interface HotelRepo extends CrudRepository<Hotel, Integer> {
	
	@Query(value = "Select H from hotel_info H where city=:city and rating >= :stars")
	List<Hotel> getHotelByCityAndStars(@Param(value = "city") String city,@Param(value="stars") float stars);

}
