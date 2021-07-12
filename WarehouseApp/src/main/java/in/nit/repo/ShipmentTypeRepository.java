package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

	@Query("SELECT COUNT(ST.shipmentCode) FROM ShipmentType ST WHERE ST.shipmentCode=:shipmentCode")  //hql query   
	public Integer getShipmentCodeCount(String shipmentCode);	

}
