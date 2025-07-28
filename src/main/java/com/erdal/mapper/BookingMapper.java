package com.erdal.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.erdal.DTO.BookingDTO;
import com.erdal.model.Booking;

public class BookingMapper {

	public static BookingDTO mapToBookingDTO(Booking booking) {

		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingStatus(booking.getBookingStatus());
		bookingDTO.setCostumerId(booking.getCostumerId());
		bookingDTO.setEndTime(booking.getEndTime());
		bookingDTO.setStartTime(booking.getStartTime());
		bookingDTO.setSaloonId(booking.getSaloonId());
		bookingDTO.setServiceOfferinIds(booking.getServiceOfferinIds());
		bookingDTO.setTotalPrice(booking.getTotalPrice());
		

		return bookingDTO;

	}


	public static Set<BookingDTO> mapAllListToCategoriesDTO(Set<Booking> bookings) {
	    return bookings.stream()
	        .map(BookingMapper::mapToBookingDTO)
	        .collect(Collectors.toSet());
	}

//		
	
	

}
