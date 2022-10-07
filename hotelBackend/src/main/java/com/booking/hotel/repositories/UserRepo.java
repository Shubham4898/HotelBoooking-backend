package com.booking.hotel.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.booking.hotel.models.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	
	public List<User> findByNumber(String number);

}
