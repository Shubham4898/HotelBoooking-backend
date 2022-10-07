package com.booking.hotel.services;

import java.util.List;

import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.exchanges.PostReviewRequest;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.Review;

public interface ReviewService {
	
    public Review addReview(int customerId, int hotelId, PostReviewRequest request);
    
}
