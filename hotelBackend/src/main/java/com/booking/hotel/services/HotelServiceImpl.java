package com.booking.hotel.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.exchanges.AddHotelRequest;
import com.booking.hotel.exchanges.CreateUserRequest;
import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.exchanges.UpdateRoomRequest;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.HotelFacility;
import com.booking.hotel.models.RoomInfo;
import com.booking.hotel.models.User;
import com.booking.hotel.repositories.HotelFacilityRepo;
import com.booking.hotel.repositories.HotelRepo;
import com.booking.hotel.repositories.RoomInfoRepo;

@Service
public class HotelServiceImpl implements HotelService {
    
	@Autowired
	HotelRepo repo;
	
	@Autowired
	RoomInfoRepo roomInfoRepo;
	
	@Autowired
	BookingService bookingService;

	
	@Override
	public Hotel addHotel(AddHotelRequest request) {
		
		
		HotelFacility hotelFacility = new HotelFacility(request.isWifi(),request.isDining(),request.isAc(),request.isMeals(),request.isFreeBreakfast());
		Hotel hotel = new Hotel(request.getName(),request.getCity(),request.getAddress(),hotelFacility);
		Hotel addedHotel = repo.save(hotel);
		return addedHotel;
	}


	@Override
	public void removeHotel(int id) {
		
	  repo.deleteById(id);	
	}


	@Override
	public Hotel updateHotel(int id, AddHotelRequest request) {
		
		Optional<Hotel> hotelOptional = repo.findById(id);
		
		if(!hotelOptional.isPresent()) {
			 return null;
		}
		
		Hotel hotel = hotelOptional.get();
		
		hotel.setName(request.getName());
		hotel.setCity(request.getCity());
		hotel.setAddress(request.getAddress());
		
		HotelFacility facility = hotel.getHotelFacility();
		facility.setAc(request.isAc());
		facility.setFreeBreakfast(request.isFreeBreakfast());
		facility.setMeals(request.isMeals());
		facility.setRestaurant(request.isDining());
		facility.setWifi(request.isWifi());
		
		repo.save(hotel);

		return hotel;
	}


	@Override
	public void updateHotelRating(Hotel hotel, float rating) {
		
		hotel.setRating(rating);
		repo.save(hotel);
	}


	@Override
	public RoomInfo updateRooms(int id, UpdateRoomRequest request) {
		
	Optional<Hotel> hotelOptional  = repo.findById(id);
	
	if(hotelOptional.isPresent()) {
		Hotel hotel = hotelOptional.get();
		
	    RoomInfo room = new RoomInfo(hotel,request.getTotalRooms(),request.getOccupiedRooms(),request.getAvailableRoms());
	    
	    RoomInfo updatedInfo = roomInfoRepo.save(room);
	    
	    return updatedInfo;
	}
	
	 return null;
  }


	@Override
	public List<Hotel> getRestaurant(GetRestauratRequest request) {
     
		List<Hotel> hotels  = repo.getHotelByCityAndStars(request.getCity(),request.getStars());
		
		if(request.getCheckInDate() != null && request.getCheckOutDate() != null) {
			hotels =  bookingService.checkAvailablity(hotels,request);
		}
		return hotels;
	}

	
}
