package company.api.contract.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import company.domain.model.Project;

public class ProjectRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 3, max = 8)
	private String code;

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Project toModel() {
		return new Project(name, code);
	}
}
