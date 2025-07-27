package com.erdal.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.erdal.status.BookingStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long saloonId;

	private Long costumerId;

	@ElementCollection
	private Set<Long> serviceOfferinIds;

	private BookingStatus bookingStatusÏ = BookingStatus.PENDING;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private int totalServices;

}
