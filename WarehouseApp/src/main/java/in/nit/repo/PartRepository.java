package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
