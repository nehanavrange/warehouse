package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.WhUserType;

public interface IWhUserTypeService {
	//non-select operation
		Integer saveWhUserType(WhUserType user);
		void updateWhUserType(WhUserType user);
		void deleteWhUserType(Integer id);
		
		//select operation
		Optional<WhUserType> getOneWhUserType(Integer id);
		List<WhUserType> getAllWhUserType();
		boolean isWhUserTypeExist(Integer id);
		
}
