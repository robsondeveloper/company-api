package company.api.contract.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import company.domain.model.User;

public class UserRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User toModel() {
		String password = "123456"; // TODO must generate a random password
		return new User(name, email, password);
	}

}
