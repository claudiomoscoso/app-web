package cl.buildersoft.business.enterprise.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.business.enterprise.beans.Employee;

public interface EmployeeService {
	public Employee getEmployee(Connection conn, Long id);

	public Employee getEmployee(Connection conn, HttpServletRequest request);

}
