package com.team3.employee.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.team3.employee.beans.Employee;

public interface EmployeeReposatory extends MongoRepository<Employee, Integer> {
	public List<Employee> findByName(String name);
}
