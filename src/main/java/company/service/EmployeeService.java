package company.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import company.api.contract.request.EmployeeRequest;
import company.api.contract.response.EmployeeResponse;
import company.domain.model.Employee;
import company.domain.repository.EmployeeRepository;
import company.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

	private EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public List<EmployeeResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toUnmodifiableList());
	}

	public EmployeeResponse findById(UUID id) {
		Employee employee = findBy(id);
		return toResponse(employee);
	}

	@Transactional
	public EmployeeResponse create(EmployeeRequest request) {
		Employee employee = request.toModel();
		return toResponse(repository.save(employee));
	}

	@Transactional
	public EmployeeResponse update(UUID id, EmployeeRequest request) {
		Employee employee = findBy(id);
		BeanUtils.copyProperties(request.toModel(), employee, "id");
		return toResponse(repository.save(employee));
	}

	@Transactional
	public void delete(UUID id) {
		Employee employee = findBy(id);
		repository.delete(employee);
	}

	private Employee findBy(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	private EmployeeResponse toResponse(Employee employee) {
		return new EmployeeResponse(employee);
	}

}
