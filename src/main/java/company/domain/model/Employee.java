package company.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	private UUID id;

	private String name;

	private LocalDate birth;

	private BigDecimal salary;

	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Employee() {
		this.id = UUID.randomUUID();
	}

	public Employee(String name, LocalDate birth, BigDecimal salary) {
		this();
		this.name = name;
		this.birth = birth;
		this.salary = salary;
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

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
