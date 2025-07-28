package com.erdal.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.erdal.status.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {
	
//	private Long id;

	private Long saloonId;

	private Long costumerId;

	private Set<Long> serviceOfferinIds;

	private BookingStatus bookingStatus;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private Integer totalPrice;
}
