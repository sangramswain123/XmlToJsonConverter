package com.lendingkart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(CustomXmlJsonConvertionException.class)
    public ResponseEntity<AppException> handleXmlJsonConversionException(CustomXmlJsonConvertionException ex) {
		
		AppException appEx = new AppException();
		appEx.setExCode("EX0001");
		appEx.setExDesc(ex.getMessage());
		
		return new ResponseEntity<AppException>(appEx, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
