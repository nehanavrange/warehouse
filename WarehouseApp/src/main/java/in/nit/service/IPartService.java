package in.nit.service;

import java.util.List;
import java.util.Optional;


import in.nit.model.Part;

public interface IPartService {

	//non-select operation
		Integer savePart(Part om);
		void updatePart(Part om);
		void deletePart(Integer id);
		
		//select operation
		Optional<Part> getOnePart(Integer id);
		List<Part> getAllPart();
		boolean isPartExist(Integer id);
		
}
