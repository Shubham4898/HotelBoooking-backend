package com.booking.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data@NoArgsConstructor
@Entity(name = "user_info")
public class User{
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	@Column(name ="phone_number")
	private String number;
	
	public User(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	
	
}
