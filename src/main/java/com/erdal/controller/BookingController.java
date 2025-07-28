package com.erdal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erdal.DTO.BookingDTO;
import com.erdal.DTO.SaloonDTO;
import com.erdal.DTO.ServiceOfferingDTO;
import com.erdal.DTO.UserDTO;
import com.erdal.requests.BookingRequest;
import com.erdal.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
	
	private final BookingService bookingService;
	
	
	public ResponseEntity<BookingDTO> createBooking(@RequestParam Long saloonId,@RequestBody BookingRequest bookingRequest){ 
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(1L);
		
		Set<ServiceOfferingDTO> serviceOfferingDTOs=new HashSet<>();
		
		
		
	}

}
