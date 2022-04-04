package com.team3.employee.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Satyaa
 * @since February, 2022
 * @see Employee Beans
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Integer id;

	private String name;
	private String password;
	private String email;
	private long phone;
}
