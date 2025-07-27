package com.erdal.exeptions;

public class BookingNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long id;

	public BookingNotFoundExeption(String message) {
		super(message);
	}

	public BookingNotFoundExeption(String message, Long id) {
		super(message);
		this.id = id;

	}
}