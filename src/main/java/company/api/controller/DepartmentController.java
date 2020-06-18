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

import company.api.contract.request.DepartmentRequest;
import company.api.contract.response.DepartmentResponse;
import company.api.contract.response.EmployeeFromDepartmentResponse;
import company.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService service;

	@GetMapping
	public List<DepartmentResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public DepartmentResponse findById(@PathVariable UUID id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DepartmentResponse create(@Valid @RequestBody DepartmentRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public DepartmentResponse update(@PathVariable UUID id, @Valid @RequestBody DepartmentRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		service.delete(id);
	}

	@PutMapping("/{departmentId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addEmployee(@PathVariable UUID departmentId, @PathVariable UUID employeeId) {
		service.addEmployee(departmentId, employeeId);
	}

	@DeleteMapping("/{departmentId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeEmployee(@PathVariable UUID departmentId, @PathVariable UUID employeeId) {
		service.removeEmployee(departmentId, employeeId);
	}

	@GetMapping("/{departmentId}/employees")
	public List<EmployeeFromDepartmentResponse> findAllEmployeeByDepartment(@PathVariable UUID departmentId) {
		return service.findAllEmployeeByDepartment(departmentId);
	}

}
