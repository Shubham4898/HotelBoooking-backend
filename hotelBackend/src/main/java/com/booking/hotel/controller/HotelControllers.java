package com.booking.hotel.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.booking.hotel.exchanges.AddCheckIn;
import com.booking.hotel.exchanges.AddHotelRequest;
import com.booking.hotel.exchanges.CreateUserRequest;
import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.exchanges.PostReviewRequest;
import com.booking.hotel.exchanges.UpdateRoomRequest;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.Review;
import com.booking.hotel.models.RoomInfo;
import com.booking.hotel.models.User;
import com.booking.hotel.services.BookingService;
import com.booking.hotel.services.HotelService;
import com.booking.hotel.services.ReviewService;
import com.booking.hotel.services.UserService;

@RestController
@RequestMapping("/api")
public class HotelControllers {

	
	@Autowired
	UserService userService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/create/user")
	public ResponseEntity<User> postCustomerData(@RequestBody CreateUserRequest userRequest){
		
		User user = userService.addUser(userRequest);
		return  ResponseEntity.accepted().body(user);
	}
	
	@GetMapping("/get/user/{number}")
	public ResponseEntity<User> getUserData(@PathVariable String number) {
		
		User user = userService.getUser(number);
		
		return ResponseEntity.ok().body(user);
		
	}
	
	@PutMapping("/update/user/{id}")
	public ResponseEntity<User> updateUserData(@PathVariable int userId, @RequestBody CreateUserRequest userRequest){
		
		User user = userService.updateUser(userId,userRequest);
		if(user == null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.accepted().body(user);
	}
	
	
	@PostMapping("/add/hotel")
	public ResponseEntity<Hotel> addHotel(@RequestBody AddHotelRequest hotelRequest){
		
		Hotel hotel = hotelService.addHotel(hotelRequest);
		return ResponseEntity.accepted().body(hotel);
	}
	
	@DeleteMapping("/remove/hotel/{hotelId}")
	public ResponseEntity<String> removeHotel(@PathVariable int hotelId ){ 
		
		hotelService.removeHotel(hotelId);
		
		return ResponseEntity.ok().body("successful");
	}
	
	@PutMapping("/update/hotel/{hotelId}")
	public ResponseEntity<Hotel> updateHotelInfo(@PathVariable int hotelId, 
			@RequestBody AddHotelRequest requestBody){
		
		Hotel hotel = hotelService.updateHotel( hotelId, requestBody);
		
		if(hotel == null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.accepted().body(hotel);
	}
	
	
	@PostMapping("/post/review/{hotelId}/{customerId}")
	public ResponseEntity<Review> postReview(@PathVariable int hotelId,
			@PathVariable int customerId,@RequestBody PostReviewRequest request){
		
		Review review = reviewService.addReview(customerId, hotelId, request);
		
		if(review == null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(review);
		
	}
	
	@PostMapping("/add/CheckIn/{customerId}/{hotelId}")
	public ResponseEntity<Booking> addCheckIn(@PathVariable int hotelId,
			@PathVariable int customerId,@RequestBody AddCheckIn addCheckIn  ){
		
		Booking booking = bookingService.addCheckIn(hotelId, customerId, addCheckIn);
		
		if(booking == null) {
			ResponseEntity.badRequest().body(null);
		}
	
		return ResponseEntity.ok().body(booking);
		
	}
	
	@PostMapping("/update/RoomInfo/{hotelId}")
	public ResponseEntity<RoomInfo> updateRooms(@PathVariable int hotelId,
			@RequestBody UpdateRoomRequest request){
		
		 RoomInfo info  = hotelService.updateRooms(hotelId,request);
		 
		 if(info != null) {
			 return ResponseEntity.badRequest().body(null);
		 }
		 
		 return ResponseEntity.accepted().body(info);
		
	}
	
	@GetMapping("/search/hotel")
	public ResponseEntity<List<Hotel>> searchHotel(@Valid @RequestBody GetRestauratRequest request){
		
		List<Hotel> hotelList = hotelService.getRestaurant(request);
		
		return ResponseEntity.ok().body(hotelList);
	}
	
}
