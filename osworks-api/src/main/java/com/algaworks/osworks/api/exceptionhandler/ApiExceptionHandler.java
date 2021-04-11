package com.algaworks.osworks.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // This is from Spring and manger all components from Controllers
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{ // Mask the API errors. But is important has information. So observe
										 // CTRL+Letf button to see the Class
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle("A or more fields are invalid. Type with prudence. Try again");
		problem.setDateTime(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
}
