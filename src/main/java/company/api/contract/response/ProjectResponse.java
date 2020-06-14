package company.api.contract.response;

import java.util.UUID;

import company.domain.model.Project;

public class ProjectResponse {

	private UUID id;
	private String name;
	private String code;

	public ProjectResponse(Project project) {
		this.id = project.getId();
		this.name = project.getName();
		this.code = project.getCode();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

}
