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
import company.domain.model.Employee;
import company.domain.repository.DepartmentRepository;
import company.domain.repository.EmployeeRepository;
import company.exception.ResourceNotFoundException;

@Service
public class DepartmentService {

	private DepartmentRepository repository;
	private EmployeeRepository employeeRepository;

	public DepartmentService(DepartmentRepository repository, EmployeeRepository employeeRepository) {
		this.repository = repository;
		this.employeeRepository = employeeRepository;
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
		if (repository.existsByNumber(department.getNumber())) {
			throw new IllegalArgumentException("Número já existente!");
		}
		return toResponse(repository.save(department));
	}

	@Transactional
	public DepartmentResponse update(UUID id, DepartmentRequest request) {
		Department department = findBy(id);
		Department departmentRequest = request.toModel();
		if (repository.existsByNumberAndIdNot(departmentRequest.getNumber(), department.getId())) {
			throw new IllegalArgumentException("Número já existente!");
		}
		BeanUtils.copyProperties(departmentRequest, department, "id");
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

	@Transactional
	public void addEmployee(UUID departmentId, UUID employeeId) {
		Department department = findBy(departmentId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException());
		employee.setDepartment(department);
		employeeRepository.save(employee);
	}

	@Transactional
	public void removeEmployee(UUID departmentId, UUID employeeId) {
		findBy(departmentId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException());
		employee.setDepartment(null);
		employeeRepository.save(employee);
	}

}
