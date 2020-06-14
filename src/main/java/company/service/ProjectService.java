package company.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import company.api.contract.request.ProjectRequest;
import company.api.contract.response.ProjectResponse;
import company.domain.model.Project;
import company.domain.repository.ProjectRepository;
import company.exception.ResourceNotFoundException;

@Service
public class ProjectService {

	private ProjectRepository repository;

	public ProjectService(ProjectRepository repository) {
		this.repository = repository;
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
		return toResponse(repository.save(project));
	}

	@Transactional
	public ProjectResponse update(UUID id, ProjectRequest request) {
		Project project = findBy(id);
		BeanUtils.copyProperties(request.toModel(), project, "id");
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

}
