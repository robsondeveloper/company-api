package company.api.contract.response;

import java.util.UUID;

import company.domain.model.Employee;

public class EmployeeFromProjectResponse {

	private UUID id;
	private String name;

	public EmployeeFromProjectResponse(Employee employee) {
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
