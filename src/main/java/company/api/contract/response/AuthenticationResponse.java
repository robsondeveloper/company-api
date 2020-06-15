package company.api.contract.response;

import java.util.Set;

import company.domain.model.User;

public class AuthenticationResponse {

	private String username;
	private Set<String> roles;
	private String token;

	public AuthenticationResponse(User user, String token) {
		this(user.getUsername(), user.getNamesOfRoles(), token);
	}

	public AuthenticationResponse(String username, Set<String> roles, String token) {
		this.username = username;
		this.roles = roles;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

}
