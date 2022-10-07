package com.booking.hotel.exchanges;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class PostReviewRequest {
   
	private int stars;
	private String comments;
}
