package com.booking.hotel.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.exchanges.AddCheckIn;
import com.booking.hotel.exchanges.GetRestauratRequest;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.models.RoomInfo;
import com.booking.hotel.models.User;
import com.booking.hotel.repositories.BookingRepo;
import com.booking.hotel.repositories.HotelRepo;
import com.booking.hotel.repositories.RoomInfoRepo;
import com.booking.hotel.repositories.UserRepo;
import com.booking.hotel.utils.SearchCallable;
import com.booking.hotel.utils.Utility;

@Service
public class BookingServiceImpl implements  BookingService {
	@Autowired
	HotelRepo hotelRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BookingRepo repo;
	@Autowired
	RoomInfoRepo roomRepo;

	@Override
	public Booking addCheckIn(int hotelId, int userId, AddCheckIn addCheckIn) {
		
		Optional<Hotel> hotelOptional  = hotelRepo.findById(hotelId);
		
		if(hotelOptional.isPresent()) {
			Hotel hotel = hotelOptional.get();
			
			RoomInfo info = roomRepo.findByHotelId(hotelId);
			
			if(info.getAvailableRooms() >= addCheckIn.getRooms()) {
			Optional<User> userOptional  = userRepo.findById(userId);
			
		
			if(userOptional.isPresent()) {        
				User user = userOptional.get();
				Booking booking = new Booking(hotel,user,addCheckIn.getCheckIn(),addCheckIn.getCheckOut(),addCheckIn.getRooms());
				Booking savedEntry = repo.save(booking);
				int roomsAvailable = info.getAvailableRooms() - addCheckIn.getRooms();
				info.setAvailableRooms(roomsAvailable);
				roomRepo.save(info);    //updating available rooms info after successful booking
				return savedEntry;
			}
			
			}
		}
		
		return null;
	}

	@Override
	public List<Hotel> checkAvailablity(List<Hotel> hotels, GetRestauratRequest request) {

		List<Callable<Hotel>> callbaleList = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Hotel>> futureList = new ArrayList<>();
		List<Hotel> resultList = new ArrayList<>();
		
		for(Hotel hotel : hotels) {
			
			callbaleList.add(new SearchCallable(repo,roomRepo,hotel.getId(),request.getCheckInDate(), request.getCheckOutDate(),request.getRooms(),hotel));
		}
		
		for(Callable<Hotel> callable : callbaleList) {
			futureList.add(executor.submit(callable));
		}
		
	
		for(Future<Hotel> templist : futureList) {
			
			try {
				if(templist.get() != null) {
					resultList.add(templist.get());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getAc() != null) {
		   resultList =  resultList.stream().filter(h -> h.getHotelFacility().isAc() == request.getAc()).collect(Collectors.toList());
		}
		
		if(request.getDining() != null) {
			resultList =  resultList.stream().filter(h -> h.getHotelFacility().isRestaurant() == request.getDining()).collect(Collectors.toList());
		}
		if(request.getWifi() != null) {
			resultList =  resultList.stream().filter(h -> h.getHotelFacility().isWifi() == request.getWifi()).collect(Collectors.toList());
		}
		if(request.getFreeBreakfast() != null) {
			resultList =  resultList.stream().filter(h -> h.getHotelFacility().isFreeBreakfast() == request.getFreeBreakfast()).collect(Collectors.toList());
		}
		
		return resultList;
		
	}

}
