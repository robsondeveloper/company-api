package company.api.contract.response;

import java.util.UUID;

import company.domain.model.Employee;

public class EmployeeFromDepartmentResponse {

	private UUID id;
	private String name;

	public EmployeeFromDepartmentResponse(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
