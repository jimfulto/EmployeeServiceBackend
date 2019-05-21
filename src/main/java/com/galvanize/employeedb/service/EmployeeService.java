package com.galvanize.employeedb.service;

import com.galvanize.employeedb.model.Employee;

import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();
}