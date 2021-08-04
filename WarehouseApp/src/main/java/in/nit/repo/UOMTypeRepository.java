package in.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.model.UOMType;

public interface UOMTypeRepository extends JpaRepository<UOMType, Integer> {

	@Query("SELECT id,uomModel FROM UOMType")
	List<Object[]> getUomIdAndModel();
	
}
