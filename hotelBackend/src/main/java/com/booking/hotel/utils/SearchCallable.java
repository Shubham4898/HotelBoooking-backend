package com.booking.hotel.utils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.RoomInfo;
import com.booking.hotel.repositories.BookingRepo;
import com.booking.hotel.repositories.GetTotalSum;
import com.booking.hotel.repositories.RoomInfoRepo;


public class SearchCallable implements Callable<Hotel> {
	
	private BookingRepo bookingRepo;
	private RoomInfoRepo roomRepo;
	private int hotelId;
	private Date checkInDate;
	private Date checkOutDate;
	private int requiredRooms;
	private Hotel hotel;

	
	
	
	
	
	public SearchCallable(BookingRepo bookingRepo, RoomInfoRepo roomRepo, int hotelId, Date checkInDate,
			Date checkOutDate,int requiredRooms, Hotel hotel) {
		super();
		this.bookingRepo = bookingRepo;
		this.roomRepo = roomRepo;
		this.hotelId = hotelId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.requiredRooms = requiredRooms;
		this.hotel = hotel;
	}






	@Override
	public Hotel call() throws Exception {
		
		 GetTotalSum sum =   bookingRepo.getAvailableRoomCount(hotelId, checkInDate);
		 int notAvailRooms = sum.getCount();      // no. of rooms that are alreadBooked for this hotel
		 
		 RoomInfo  info = roomRepo.findByHotelId(hotelId);
		 
		 if(info.getTotalRooms()-notAvailRooms >= requiredRooms) {
			 return hotel;                 /* returning hotel object if
			                                  room is available else null */
		 }
		 
		 
		 return null;
		 
        
	}
	

	
	

	
	
	

}
