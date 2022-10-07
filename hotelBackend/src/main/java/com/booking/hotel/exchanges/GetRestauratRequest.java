package com.booking.hotel.exchanges;

import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data@NoArgsConstructor
public class GetRestauratRequest {
    
	@NotNull
	private String city;
	private int rooms;
	private Date checkInDate;
	private Date checkOutDate;
	private float stars;
	private Boolean wifi;
	private Boolean dining;
	private Boolean ac;
	private Boolean freeBreakfast;
}
