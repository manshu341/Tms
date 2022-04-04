package com.employee.exception;

/**
 * 
 * @author Satyaa
 * @see EmployeeNotFoundException
 * @since February, 2022
 *
 */
public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public EmployeeNotFoundException() {
		this.msg = "User not found";
	}

	public EmployeeNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "EmployeeNotFoundException " + msg ;
	}

}
