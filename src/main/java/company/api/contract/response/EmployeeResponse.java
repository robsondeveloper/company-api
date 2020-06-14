package company.api.contract.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import company.domain.model.Employee;

public class EmployeeResponse {

	private UUID id;

	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birth;

	private BigDecimal salary;

	public EmployeeResponse(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.birth = employee.getBirth();
		this.salary = employee.getSalary();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public BigDecimal getSalary() {
		return salary;
	}

}
