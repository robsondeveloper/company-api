package company.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import company.domain.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

	boolean existsByNumber(Integer number);

	boolean existsByNumberAndIdNot(Integer number, UUID id);

}
