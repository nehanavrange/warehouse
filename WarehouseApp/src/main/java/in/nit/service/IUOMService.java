package in.nit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.nit.model.UOMType;

public interface IUOMService {

	//non-select operation
	Integer saveUOMType(UOMType st);
	void updateUOMType(UOMType st);
	void deleteUOMType(Integer id);
	
	//select operation
	Optional<UOMType> getOneUOMType(Integer id);
	List<UOMType> getAllUOMType();
	boolean isUOMTypeExist(Integer id);

	//list<Object[]> converted to Map
	Map<Integer,String> getUomIdAndModel();
	
}
