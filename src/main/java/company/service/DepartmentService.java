package company.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import company.api.contract.request.DepartmentRequest;
import company.api.contract.response.DepartmentResponse;
import company.domain.model.Department;
import company.domain.repository.DepartmentRepository;
import company.exception.ResourceNotFoundException;

@Service
public class DepartmentService {

	private DepartmentRepository repository;

	public DepartmentService(DepartmentRepository repository) {
		this.repository = repository;
	}

	public List<DepartmentResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toUnmodifiableList());
	}

	public DepartmentResponse findById(UUID id) {
		Department department = findBy(id);
		return toResponse(department);
	}

	@Transactional
	public DepartmentResponse create(DepartmentRequest request) {
		Department department = request.toModel();
		return toResponse(repository.save(department));
	}

	@Transactional
	public DepartmentResponse update(UUID id, DepartmentRequest request) {
		Department department = findBy(id);
		BeanUtils.copyProperties(request.toModel(), department, "id");
		return toResponse(repository.save(department));
	}

	@Transactional
	public void delete(UUID id) {
		Department department = findBy(id);
		repository.delete(department);
	}

	private Department findBy(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	private DepartmentResponse toResponse(Department department) {
		return new DepartmentResponse(department);
	}

}
