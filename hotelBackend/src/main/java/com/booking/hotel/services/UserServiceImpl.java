package com.booking.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.exchanges.CreateUserRequest;
import com.booking.hotel.models.User;
import com.booking.hotel.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	UserRepo userRepo;
	
	@Override
	public User addUser(CreateUserRequest userRequest) {
		
       String name = userRequest.getName();
       String number = userRequest.getNumber();
       
       User user = new User(name,number);
       
        User addedUser = userRepo.save(user);
        
        return addedUser;
	}

	@Override
	public User getUser(String number) {
		
		List<User> userList = userRepo.findByNumber(number);
		
		return userList.get(0);
	}

	@Override
	public User updateUser(int id, CreateUserRequest userRequest) {
		
		 Optional<User> userOptional =   userRepo.findById(id);
		 
		 if(!userOptional.isPresent()) {
			 return null;
		 }
		 
		 User user = userOptional.get();
		 
		 user.setName(userRequest.getName());
		 user.setNumber(userRequest.getNumber());
		 
		 User updatedUser =  userRepo.save(user);
		return updatedUser;
	}

}
