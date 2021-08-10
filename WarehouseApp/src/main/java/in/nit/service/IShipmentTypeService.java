package in.nit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.nit.model.ShipmentType;

public interface IShipmentTypeService {

	//non-select operation
	Integer saveShipmentType(ShipmentType st);
	void updateShipmentType(ShipmentType st);
	void deleteShipmentType(Integer id);
	
	//select operation
	Optional<ShipmentType> getOneShipmentType(Integer id);
	List<ShipmentType> getAllShipmentType();
	boolean isShipmentTypeExist(Integer id);
	
	boolean isShipmentTypeCodeExist(String shipmentCode);
	
	//used in integration with purchaseOrder
	public Map<Integer,String> getShipmentIdAndCode();
}
