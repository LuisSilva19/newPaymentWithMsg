package com.microservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
}
