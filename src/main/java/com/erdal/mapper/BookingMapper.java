package com.erdal.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.erdal.DTO.BookingDTO;
import com.erdal.model.Booking;

public class BookingMapper {

	public static BookingDTO mapToBookingDTO(Booking booking) {

		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setId(booking.getId());
		bookingDTO.setBookingStatus(booking.getBookingStatus());
		bookingDTO.setCustumerId(booking.getCustumerId());
		bookingDTO.setEndTime(booking.getEndTime());
		bookingDTO.setStartTime(booking.getStartTime());
		bookingDTO.setSaloonId(booking.getSaloonId());
		bookingDTO.setServiceOfferinIds(booking.getServiceOfferinIds());
		bookingDTO.setTotalPrice(booking.getTotalPrice());
		

		return bookingDTO;

	}
	public static Booking mapDtoToBooking(BookingDTO bookingDTO) {
		
		Booking booking = new Booking();
		booking.setId(bookingDTO.getId());
		booking.setBookingStatus(bookingDTO.getBookingStatus());
		booking.setCustumerId(bookingDTO.getCustumerId());
		booking.setEndTime(bookingDTO.getEndTime());
		booking.setStartTime(bookingDTO.getStartTime());
		booking.setSaloonId(bookingDTO.getSaloonId());
		booking.setServiceOfferinIds(bookingDTO.getServiceOfferinIds());
		booking.setTotalPrice(bookingDTO.getTotalPrice());
		
		
		return booking;
		
	}
	


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
