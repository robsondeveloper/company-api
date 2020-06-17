package company.domain.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Project {

	@Id
	private UUID id;

	private String name;

	private String code;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "project_employee",
		joinColumns = @JoinColumn(name = "project_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "employee_id", nullable = false))
	private Set<Employee> employees;

	public Project() {
		this.id = UUID.randomUUID();
	}

	public Project(String name, String code) {
		this();
		this.name = name;
		this.code = code;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
