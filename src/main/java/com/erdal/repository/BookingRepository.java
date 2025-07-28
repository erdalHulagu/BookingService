package com.erdal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erdal.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
	
	List<Booking> findByCustomerId(Long costomerId);
	
	List<Booking> findBySaloonId(Long saloonId);

}
