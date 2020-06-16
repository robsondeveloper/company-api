package company.domain.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	private UUID id;

	private String name;

	private Integer number;

	@OneToMany(mappedBy = "department")
	private Set<Employee> employees;

	public Department() {
		this.id = UUID.randomUUID();
	}

	public Department(String name, Integer number) {
		this();
		this.name = name;
		this.number = number;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
