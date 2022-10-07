package com.booking.hotel.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "booking")
@Data@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
    
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "check_in")
    private Date checkIn;
    
    @Column(name = "check_out")
    private Date checkOut;
    
    private int rooms;

	public Booking(Hotel hotel, User user, Date checkIn, Date checkOut, int rooms) {
		super();
		this.hotel = hotel;
		this.user = user;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.rooms = rooms;
	}
    
    
    
	
	
}
