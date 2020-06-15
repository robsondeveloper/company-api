package company.api.contract.response;

import java.util.UUID;

import company.domain.model.User;

public class UserResponse {

	private UUID id;
	private String name;
	private String email;

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
