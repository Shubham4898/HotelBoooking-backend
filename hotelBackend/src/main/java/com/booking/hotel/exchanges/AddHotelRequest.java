package com.booking.hotel.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class AddHotelRequest {

	private String name;
	private String city;
	private String address;
	private boolean wifi;
	private boolean dining;
	private boolean ac;
	private boolean meals;
	private boolean freeBreakfast;
	
	
}
