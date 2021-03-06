package company.api.contract.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import company.domain.model.Department;

public class DepartmentRequest {

	@NotBlank
	private String name;

	@NotNull
	@Min(1)
	private Integer number;

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Department toModel() {
		return new Department(name, number);
	}
}
