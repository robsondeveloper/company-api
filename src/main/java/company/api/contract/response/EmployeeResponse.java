package company.api.contract.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import company.domain.model.Department;
import company.domain.model.Employee;

public class EmployeeResponse {

	private UUID id;

	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birth;

	private BigDecimal salary;

	private DepartmentResponse department;

	public EmployeeResponse(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.birth = employee.getBirth();
		this.salary = employee.getSalary();
		if (Objects.nonNull(employee.getDepartment())) {
			this.department = new DepartmentResponse(employee.getDepartment());
		}
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

	public DepartmentResponse getDepartment() {
		return department;
	}

	class DepartmentResponse {
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

}
