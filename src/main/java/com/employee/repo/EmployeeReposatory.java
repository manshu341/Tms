package com.employee.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.beans.Employee;

public interface EmployeeReposatory extends MongoRepository<Employee, Integer> {
	public List<Employee> findByName(String name);
}
