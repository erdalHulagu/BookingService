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
	
//	List<BookingDTO> getBookingsByBookingsCostemerId(Long customerId);
	
	List<BookingDTO>getBookingBySaloonId(Long saloonId);
	
	BookingDTO getBookingById(Long id);
	
	BookingDTO upDateBooking(Long id,BookingStatus bookingStatus);
	
	void deleteBookingById(Long id);
	
	List<BookingDTO>getBookingByDate(LocalDate localDate,Long saloonId);
	
	SaloonReport getSaloonReport(Long saloonId);
	

}
