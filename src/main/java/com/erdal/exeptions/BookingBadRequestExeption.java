package com.erdal.exeptions;

public class BookingBadRequestExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	public BookingBadRequestExeption(String message) {
		super(message);
	}

	public BookingBadRequestExeption(String message, Long id) {
		super(message);
		this.id = id;

	}
}
