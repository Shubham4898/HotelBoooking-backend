package com.booking.hotel.services;

import java.util.List;

import com.booking.hotel.exchanges.AddCheckIn;
import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Hotel;

public interface BookingService {
 
	public Booking addCheckIn(int hotelId,int userId, AddCheckIn addCheckIn);
	public List<Hotel> checkAvailablity(List<Hotel>hotels, GetRestauratRequest request);
}
