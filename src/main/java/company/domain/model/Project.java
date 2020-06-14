package company.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

	@Id
	private UUID id;

	private String name;

	private String code;

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

}
