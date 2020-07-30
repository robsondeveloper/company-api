package company.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import company.api.contract.request.EmployeeRequest;
import company.api.contract.response.EmployeeResponse;
import company.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/employees")
@SecurityRequirement(name = "api")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping
	public List<EmployeeResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public EmployeeResponse findById(@PathVariable UUID id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeResponse create(@Valid @RequestBody EmployeeRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public EmployeeResponse update(@PathVariable UUID id, @Valid @RequestBody EmployeeRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		service.delete(id);
	}

}
