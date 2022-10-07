package com.booking.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer> {
      
	 @Query(value = "Select count(1) as count from reviews where hotel_id=:id")
	 public GetTotalSum findNumberOfReview(@Param(value = "id") int id);
}
