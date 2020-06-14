package company.api.contract.response;

import java.util.UUID;

import company.domain.model.Department;

public class DepartmentResponse {

	private UUID id;
	private String name;
	private Integer number;

	public DepartmentResponse(Department department) {
		this.id = department.getId();
		this.name = department.getName();
		this.number = department.getNumber();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

}
