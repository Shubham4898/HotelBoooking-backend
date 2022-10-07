package com.booking.hotel.services;

import java.util.List;

import com.booking.hotel.exchanges.AddHotelRequest;
import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.exchanges.UpdateRoomRequest;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.HotelFacility;
import com.booking.hotel.models.RoomInfo;

public interface HotelService {

	public Hotel addHotel(AddHotelRequest request);
	public void removeHotel(int id);
	public Hotel updateHotel(int id,AddHotelRequest request);
	public void updateHotelRating(Hotel hotel, float rating);
	public RoomInfo updateRooms(int id,UpdateRoomRequest request);
	public List<Hotel> getRestaurant(GetRestauratRequest request);
}
