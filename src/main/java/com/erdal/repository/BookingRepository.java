package com.erdal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erdal.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{

}
