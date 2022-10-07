package com.booking.hotel.services;

import com.booking.hotel.exchanges.CreateUserRequest;
import com.booking.hotel.models.User;

public interface UserService {

	public User addUser(CreateUserRequest userRequest);
	public User getUser(String number);
	public User updateUser(int id, CreateUserRequest userRequest);
}
