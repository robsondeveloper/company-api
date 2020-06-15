package company.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import company.api.contract.request.UserRequest;
import company.api.contract.response.UserResponse;
import company.domain.model.User;
import company.domain.repository.UserRepository;
import company.exception.ResourceNotFoundException;

@Service
public class UserService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<UserResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toUnmodifiableList());
	}

	public UserResponse findById(UUID id) {
		User user = findBy(id);
		return toResponse(user);
	}

	@Transactional
	public UserResponse create(UserRequest request) {
		User user = request.toModel();
		if (repository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("email já existente!");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return toResponse(repository.save(user));
	}

	@Transactional
	public UserResponse update(UUID id, UserRequest request) {
		User user = findBy(id);
		User userRequest = request.toModel();
		Optional<User> optionalUser = repository.findByEmail(userRequest.getEmail());
		if (optionalUser.isPresent() && !optionalUser.get().getId().equals(user.getId())) {
			throw new IllegalArgumentException("email já existente!");
		}
		BeanUtils.copyProperties(userRequest, user, "id", "password");
		return toResponse(repository.save(user));
	}

	@Transactional
	public void delete(UUID id) {
		User user = findBy(id);
		repository.delete(user);
	}

	private User findBy(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	private UserResponse toResponse(User user) {
		return new UserResponse(user);
	}

}
