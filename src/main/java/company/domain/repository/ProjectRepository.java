package company.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import company.domain.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

}
