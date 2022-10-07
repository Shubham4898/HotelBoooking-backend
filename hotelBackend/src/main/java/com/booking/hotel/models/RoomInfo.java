package com.booking.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room_info")
@Data@NoArgsConstructor
public class RoomInfo {

	    @Id
	    @GeneratedValue(strategy =  GenerationType.IDENTITY)
		private int id;
	    
	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    private Hotel hotel;
	    
	    @Column(name ="total_rooms")
	    private int totalRooms;
	    
	    @Column(name ="occupied_rooms")
	    private int occupiedRooms;
	    
	    @Column(name = "available_rooms")
	    private int availableRooms;

		public RoomInfo(Hotel hotel, int totalRooms, int occupiedRooms, int availableRooms) {
			super();
			this.hotel = hotel;
			this.totalRooms = totalRooms;
			this.occupiedRooms = occupiedRooms;
			this.availableRooms = availableRooms;
		}
	    
	    
}
