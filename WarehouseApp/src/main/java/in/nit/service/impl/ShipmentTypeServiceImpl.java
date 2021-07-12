package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.model.ShipmentType;
import in.nit.repo.ShipmentTypeRepository;

@Service
public class ShipmentTypeServiceImpl implements in.nit.service.IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;
	
	@Transactional
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		return repo.save(st).getId();
	}
	
	@Transactional
	@Override
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);

	}
	
	@Transactional
	@Override
	public void deleteShipmentType(Integer id) {
	repo.deleteById(id);

	}
	@Transactional(readOnly = true)
	@Override
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt=repo.findById(id);
		
		return opt;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ShipmentType> getAllShipmentType() {
		List<ShipmentType> list=repo.findAll();
		return list;
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean isShipmentTypeExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean isShipmentTypeCodeExist(String shipmentCode) {
		int count=repo.getShipmentCodeCount(shipmentCode);
		boolean flag=(count)>=1 ? true:false;
		return flag;
	}

}
