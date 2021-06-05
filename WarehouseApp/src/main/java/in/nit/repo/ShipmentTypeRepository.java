package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

}
