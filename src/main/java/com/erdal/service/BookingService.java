package com.erdal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.erdal.DTO.BookingDTO;
import com.erdal.DTO.SaloonDTO;
import com.erdal.DTO.ServiceOfferingDTO;
import com.erdal.DTO.UserDTO;
import com.erdal.model.Booking;
import com.erdal.model.SaloonReport;
import com.erdal.requests.BookingRequest;
import com.erdal.status.BookingStatus;

public interface BookingService {
	
	BookingDTO createBooking(BookingRequest bookingRequest,UserDTO userDTO,SaloonDTO saloonDTO,Set<ServiceOfferingDTO> serviceOfferingDTOs);
	
	List<Booking> getBookingsByBookingsCostemerId(Long customerId);
	
	List<Booking>getBookingBySaloonId(Long saloonId);
	
	Booking getBookingById(Long id);
	
	Booking upDateBooking(Long id,BookingStatus bookingStatus);
	
	void deleteBookingById(Long id);
	
	List<Booking>getBookingByDate(LocalDate localDate,Long saloonId);
	
	SaloonReport getSaloonReport(Long saloonId);
	

}
