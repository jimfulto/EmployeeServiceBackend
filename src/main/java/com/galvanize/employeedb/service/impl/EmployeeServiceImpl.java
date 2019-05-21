package com.galvanize.employeedb.service.impl;

import com.galvanize.employeedb.dao.EmployeeDao;
import com.galvanize.employeedb.model.Employee;
import com.galvanize.employeedb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		return employees;
	}

}