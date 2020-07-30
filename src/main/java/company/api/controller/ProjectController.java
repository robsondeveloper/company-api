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

import company.api.contract.request.ProjectRequest;
import company.api.contract.response.EmployeeFromProjectResponse;
import company.api.contract.response.ProjectResponse;
import company.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/projects")
@SecurityRequirement(name = "api")
public class ProjectController {

	@Autowired
	private ProjectService service;

	@GetMapping
	public List<ProjectResponse> findAll() {
		return service.findAll();
	}

	@Operation(summary = "Get a Project by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Project", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid uuid supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Project not found", content = @Content) })
	@GetMapping("/{id}")
	public ProjectResponse findById(@Parameter(description = "uuid of Project to be searched") @PathVariable UUID id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResponse create(@Valid @RequestBody ProjectRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public ProjectResponse update(@PathVariable UUID id, @Valid @RequestBody ProjectRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		service.delete(id);
	}

	@PutMapping("/{projectId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addEmployee(@PathVariable UUID projectId, @PathVariable UUID employeeId) {
		service.addEmployee(projectId, employeeId);
	}

	@DeleteMapping("/{projectId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeEmployee(@PathVariable UUID projectId, @PathVariable UUID employeeId) {
		service.removeEmployee(projectId, employeeId);
	}

	@GetMapping("/{projectId}/employees")
	public List<EmployeeFromProjectResponse> findAllEmployeeByProject(@PathVariable UUID projectId) {
		return service.findAllEmployeeByProject(projectId);
	}

}
