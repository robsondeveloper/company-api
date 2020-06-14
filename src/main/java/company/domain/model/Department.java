package company.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {

	@Id
	private UUID id;

	private String name;

	private Integer number;

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

}
