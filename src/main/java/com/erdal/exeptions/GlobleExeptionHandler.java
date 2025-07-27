package com.erdal.exeptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.erdal.exeptions.response.BookingExeptionResponse;

@ControllerAdvice
public class GlobleExeptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BookingExeptionResponse> exeptionHandler(Exception ex, WebRequest req){ 
		
		BookingExeptionResponse exeptionResponse= new BookingExeptionResponse(ex.getMessage(),req.getDescription(false),LocalDateTime.now());
		
	return	ResponseEntity.ok(exeptionResponse);
		
	}

}
