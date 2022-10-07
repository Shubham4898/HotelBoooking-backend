package com.booking.hotel.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
@Entity(name = "hotel_info")
public class Hotel {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String city;
	private String address;
	private float rating;
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "facility")
	private HotelFacility hotelFacility;
	
	public Hotel(String name, String city, String address,HotelFacility facility) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.hotelFacility = facility;
	}
	
	
}
