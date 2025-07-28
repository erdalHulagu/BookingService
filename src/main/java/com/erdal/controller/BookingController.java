package com.erdal.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.erdal.DTO.BookingDTO;
import com.erdal.DTO.BookingSlotDTO;
import com.erdal.DTO.SaloonDTO;
import com.erdal.DTO.ServiceOfferingDTO;
import com.erdal.DTO.UserDTO;
import com.erdal.model.Booking;
import com.erdal.model.SaloonReport;
import com.erdal.requests.BookingRequest;
import com.erdal.service.BookingService;
import com.erdal.status.BookingStatus;

import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private  BookingService bookingService;
	
	
	@PostMapping
	public ResponseEntity<BookingDTO> createBooking(@RequestParam Long saloonId,@RequestBody BookingRequest bookingRequest){ 
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(1L);
		
		SaloonDTO saloonDto=new SaloonDTO();
		saloonDto.setId(saloonId);
		
		Set<ServiceOfferingDTO> serviceOfferingDTOs=new HashSet<>();
		
		ServiceOfferingDTO serviceOfferingDTO=new ServiceOfferingDTO();
		
		serviceOfferingDTO.setId(1L);
		serviceOfferingDTO.setPrice(321);
		serviceOfferingDTO.setDuration(45);
		serviceOfferingDTO.setOfferingName("hair cut for man");
		
		serviceOfferingDTOs.add(serviceOfferingDTO);
		
		BookingDTO bookingDTO =bookingService.createBooking(bookingRequest, userDTO, saloonDto, serviceOfferingDTOs);
		return ResponseEntity.ok(bookingDTO);
	}
	
//	@GetMapping("/customer")
//	public ResponseEntity<Set<BookingDTO> >getBookingByCustomer(@PathVariable Long customerId) {
//		
//		List<BookingDTO> bookingDTOs=bookingService.getBookingsByBookingsCostemerId(1L);
//		 
//		Set<BookingDTO>bookingDTOsSet=new HashSet<>(bookingDTOs);
//		return ResponseEntity.ok(bookingDTOsSet);
//	}
	
	@GetMapping("/saloon")
	public ResponseEntity<Set<BookingDTO> >getBookingBySaloon(@PathVariable Long saloonId) {
		
		List<BookingDTO> bookingDTOs=bookingService.getBookingBySaloonId(1L);
		
		Set<BookingDTO>bookingDTOsSet=new HashSet<>(bookingDTOs);
		
		return ResponseEntity.ok(bookingDTOsSet);
	}



	@GetMapping("/{id}")
	public ResponseEntity <BookingDTO> getBookingById(@PathVariable Long id) {

		BookingDTO bookingDTO = bookingService.getBookingById(id);

		return ResponseEntity.ok(bookingDTO);
	}
	
	@PutMapping
	public ResponseEntity <BookingDTO> updateBookingStatus(@PathVariable Long id, @RequestParam BookingStatus status) {

		BookingDTO bookingDTO = bookingService.upDateBooking(id,status);

		return ResponseEntity.ok(bookingDTO);
	}
	
	@GetMapping("/slot/saloon/{saloonId}/date/{date}")
	public ResponseEntity<List<BookingSlotDTO>>getBookedSlote(@PathVariable Long saloonId,@RequestParam(required = false) LocalDate date) {

		List<BookingDTO> bookingDTOs = bookingService.getBookingByDate(date,saloonId);
		
		List<BookingSlotDTO> bookingSlotDTOs = bookingDTOs.stream()
			    .map(booking -> { 
			        BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
			        bookingSlotDTO.setStartTime(booking.getStartTime());
			        bookingSlotDTO.setEndTime(booking.getEndTime());
			        return bookingSlotDTO; 
			    })
			    .collect(Collectors.toList());
		
		

		return ResponseEntity.ok(bookingSlotDTOs);
	}
	@GetMapping("/report")
	public ResponseEntity<SaloonReport>getSaloonReport() {
		SaloonReport saloonReport=bookingService.getSaloonReport(1L);
		
		
		return ResponseEntity.ok(saloonReport);
	}

}
