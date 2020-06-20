package company.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import company.api.contract.request.ProjectRequest;
import company.api.contract.response.EmployeeFromProjectResponse;
import company.api.contract.response.ProjectResponse;
import company.domain.model.Employee;
import company.domain.model.Project;
import company.domain.repository.EmployeeRepository;
import company.domain.repository.ProjectRepository;
import company.exception.ResourceNotFoundException;

@Service
public class ProjectService {

	private ProjectRepository repository;
	private EmployeeRepository employeeRepository;

	public ProjectService(ProjectRepository repository, EmployeeRepository employeeRepository) {
		this.repository = repository;
		this.employeeRepository = employeeRepository;
	}

	public List<ProjectResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toUnmodifiableList());
	}

	public ProjectResponse findById(UUID id) {
		Project project = findBy(id);
		return toResponse(project);
	}

	@Transactional
	public ProjectResponse create(ProjectRequest request) {
		Project project = request.toModel();
		if (repository.existsByCode(project.getCode())) {
			throw new IllegalArgumentException("Código já existente!");
		}
		return toResponse(repository.save(project));
	}

	@Transactional
	public ProjectResponse update(UUID id, ProjectRequest request) {
		Project project = findBy(id);
		Project projectRequest = request.toModel();
		if (repository.existsByCodeAndIdNot(projectRequest.getCode(), project.getId())) {
			throw new IllegalArgumentException("Código já existente!");
		}
		BeanUtils.copyProperties(projectRequest, project, "id");
		return toResponse(repository.save(project));
	}

	@Transactional
	public void delete(UUID id) {
		Project project = findBy(id);
		repository.delete(project);
	}

	private Project findBy(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	private ProjectResponse toResponse(Project project) {
		return new ProjectResponse(project);
	}

	@Transactional
	public void addEmployee(UUID projectId, UUID employeeId) {
		Project project = findBy(projectId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException());
		project.getEmployees().add(employee);
		repository.save(project);
	}

	@Transactional
	public void removeEmployee(UUID projectId, UUID employeeId) {
		Project project = findBy(projectId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException());
		project.getEmployees().remove(employee);
		repository.save(project);
	}

	public List<EmployeeFromProjectResponse> findAllEmployeeByProject(UUID projectId) {
		return findBy(projectId).getEmployees().stream().map(EmployeeFromProjectResponse::new)
				.collect(Collectors.toUnmodifiableList());
	}

}
