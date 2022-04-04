package com.employee.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Satyaa
 * @see Exception Response bean file
 * @since February, 2022
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ExceptionResponse {
	private String message;
	private String details;
	private Date date;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + ", details=" + details + ", date=" + date + "]";
	}
	public ExceptionResponse(String message, String details, Date date) {
		super();
		this.message = message;
		this.details = details;
		this.date = date;
	}
	public ExceptionResponse() {
		super();
	}

	
	
}
