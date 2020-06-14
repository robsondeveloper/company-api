package company.api.contract.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import company.domain.model.Employee;

public class EmployeeRequest {

	@NotBlank
	private String name;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birth;

	@NotNull
	@Positive
	private BigDecimal salary;

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Employee toModel() {
		return new Employee(name, birth, salary);
	}

}
