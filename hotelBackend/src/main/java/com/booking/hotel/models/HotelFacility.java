package com.booking.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel_facility")
@Data@NoArgsConstructor@AllArgsConstructor
public class HotelFacility {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private boolean wifi;
 @Column(name = "dining")
 private boolean restaurant;
 private boolean ac;
 private boolean meals;
 @Column(name = "free_breakfast")
 private boolean freeBreakfast;
 
public HotelFacility(boolean wifi, boolean restaurant, boolean ac, boolean meals, boolean freeBreakfast) {
	super();
	
	this.wifi = wifi;
	this.restaurant = restaurant;
	this.ac = ac;
	this.meals = meals;
	this.freeBreakfast = freeBreakfast;
}
 
 
 
 
}
