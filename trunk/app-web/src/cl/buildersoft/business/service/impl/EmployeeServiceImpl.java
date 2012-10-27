package cl.buildersoft.business.service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.exception.BSProgrammerException;

public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployee(Connection conn, Long id) {
		Employee out = new Employee();
		BSBeanUtils bu = new BSBeanUtils();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}

	@Override
	public Employee getEmployee(Connection conn, HttpServletRequest request) {
		String idAsParameter = request.getParameter("cId");
		String employeeId = null;

		if (idAsParameter != null) {
			employeeId = idAsParameter;
		} else {
			Object idAsAttribute = request.getAttribute("cId");
			if (idAsAttribute == null) {
				throw new BSProgrammerException("No hay parametro de ID de empleado");
			} else {
				employeeId = idAsAttribute.toString();
			}
		}

		Long id = Long.parseLong(employeeId);
		return getEmployee(conn, id);
	}
}
