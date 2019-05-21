package com.galvanize.employeedb.dao;

import com.galvanize.employeedb.model.Employee;


import java.util.List;

public interface EmployeeDao {
	List<Employee> getAllEmployees();
}