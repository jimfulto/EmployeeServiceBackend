package com.galvanize.employeedb.dao.impl;

import com.galvanize.employeedb.dao.EmployeeDao;
import com.galvanize.employeedb.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Employee> result = new ArrayList<Employee>();
		for (Map<String, Object> row : rows) {
			Employee emp = new Employee();
			emp.setEmpID((String) row.get("empId"));
			emp.setName((String) row.get("name"));
			emp.setAddress((String) row.get("address"));
			emp.setCity((String) row.get("city"));
			emp.setState((String) row.get("state"));
			emp.setZip((String) row.get("zip"));
			emp.setHireDate((String) row.get("hireDate"));
			result.add(emp);
		}

		return result;
	}
}