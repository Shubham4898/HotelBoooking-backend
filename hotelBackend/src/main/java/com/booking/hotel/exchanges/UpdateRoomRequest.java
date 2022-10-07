package com.booking.hotel.exchanges;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class UpdateRoomRequest {
    
	private int totalRooms;
	private int occupiedRooms;
	private int availableRoms;
}
