package company.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import company.domain.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
