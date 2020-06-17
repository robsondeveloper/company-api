package company.api.contract.response;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import company.domain.model.Employee;
import company.domain.model.Project;

public class ProjectResponse {

	private UUID id;
	private String name;
	private String code;
	private Set<EmployeeResponse> employees;

	public ProjectResponse(Project project) {
		this.id = project.getId();
		this.name = project.getName();
		this.code = project.getCode();
		this.employees = project.getEmployees().stream().map(EmployeeResponse::new)
				.collect(Collectors.toUnmodifiableSet());
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

	public Set<EmployeeResponse> getEmployees() {
		return employees;
	}

	class EmployeeResponse {
		private UUID id;
		private String name;

		public EmployeeResponse(Employee employee) {
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

}
