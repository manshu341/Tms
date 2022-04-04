package com.team3.employee.exception;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	private String message;
	private String details;
	private Date date;

}
