package com.erdal.mapper;

import java.util.List;
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
	public static Booking mapDtoToBooking(BookingDTO bookingDTO) {
		
		Booking booking = new Booking();
		booking.setBookingStatus(bookingDTO.getBookingStatus());
		booking.setCostumerId(bookingDTO.getCostumerId());
		booking.setEndTime(bookingDTO.getEndTime());
		booking.setStartTime(bookingDTO.getStartTime());
		booking.setSaloonId(bookingDTO.getSaloonId());
		booking.setServiceOfferinIds(bookingDTO.getServiceOfferinIds());
		booking.setTotalPrice(bookingDTO.getTotalPrice());
		
		
		return booking;
		
	}
	


//	public static List<BookingDTO> mapAllListToCategoriesDTO(Set<Booking> bookings) {
//	    return bookings.stream()
//	        .map(BookingMapper::mapToBookingDTO)
//	        .collect(Collectors.toSet());
//	}
	public static List<BookingDTO> mapAllListToBookingDTO(List<Booking> bookings) {
		return bookings.stream()
				.map(BookingMapper::mapToBookingDTO).collect(Collectors.toList());
	}


	public static List<Booking> mapToDtoToBooking(List<BookingDTO>bookingsDTOs) {
		
		return bookingsDTOs.stream()
				.map(BookingMapper::mapDtoToBooking).collect(Collectors.toList());
	}

//		
	
	

}
