package com.hibrido.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hibrido.api.exception.PeriodoInvalidoException;

@ControllerAdvice
public class HibridoApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({PeriodoInvalidoException.class})
	public ResponseEntity<Object> handlePeriodoInvalidoExceptionException(PeriodoInvalidoException ex, WebRequest request) {
		String title = "Período Inválido.";
		String description = ex.getMessage();
		List<Error> erros = Arrays.asList(new Error(title, description));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ResourceAccessException.class})
	public ResponseEntity<Object> handleResourceAccessException(ResourceAccessException ex, WebRequest request) {
		String title = "Erro de comunicação externa.";
		String description = ex.getMessage();
		List<Error> erros = Arrays.asList(new Error(title, description));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String title = "Formato inválido: " + ex.getName();
		String description = ex.getMessage();
		List<Error> erros = Arrays.asList(new Error(title, description));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Error{
		private String title;
		private String description;
		
		public Error(String title, String description) {	
			this.title = title;
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}
		
		
	}
}
