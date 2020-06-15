package company.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import company.api.authentication.JwtTokenManager;
import company.api.contract.request.AuthenticationRequest;
import company.api.contract.response.AuthenticationResponse;
import company.domain.model.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@PostMapping
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		User user = (User) authentication.getPrincipal();
		String jwt = jwtTokenManager.generateToken(user);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse(user, jwt);
		return ResponseEntity.ok(authenticationResponse);
	}

}
