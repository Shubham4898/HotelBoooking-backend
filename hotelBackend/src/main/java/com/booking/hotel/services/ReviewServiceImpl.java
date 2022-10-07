package com.booking.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.exchanges.PostReviewRequest;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.Review;
import com.booking.hotel.models.User;
import com.booking.hotel.repositories.GetTotalSum;
import com.booking.hotel.repositories.HotelRepo;
import com.booking.hotel.repositories.ReviewRepo;
import com.booking.hotel.repositories.UserRepo;

@Service
public class ReviewServiceImpl implements ReviewService{
    
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	ReviewRepo repo;
	
	@Override
	public Review addReview(int customerId, int hotelId, PostReviewRequest request) {
		
		Optional<User> userOptional =  userRepo.findById(customerId);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			Optional<Hotel> hotelOptional = hotelRepo.findById(hotelId);
			
			if(hotelOptional.isPresent()) {
				Hotel hotel = hotelOptional.get();
				
				Review review = new Review(hotel,user,request.getStars(),request.getComments());
				
				Review postedReview = repo.save(review);
				updateHotelAverageRating(hotel,request.getStars());
				return postedReview;
			}
		}
		
		return null;
	}
	
	
	public void updateHotelAverageRating(Hotel hotel, int stars) {
		
		float currRating = hotel.getRating();
		GetTotalSum getTotalSum = repo.findNumberOfReview(hotel.getId());
		int totalReview = getTotalSum.getCount();
		float totalScore = currRating * totalReview;
	    totalScore += stars;
		currRating = totalScore/totalReview;
		hotel.setRating(currRating);
		hotelRepo.save(hotel);
		
	}


	

}
