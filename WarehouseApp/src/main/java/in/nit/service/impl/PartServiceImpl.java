package in.nit.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.model.Part;
import in.nit.repo.PartRepository;
import in.nit.service.IPartService;

@Service
public class PartServiceImpl implements IPartService{

	@Autowired
	PartRepository repo;
	
	
	@Transactional
	@Override
	public Integer savePart(Part om) {
		
		return repo.save(om).getId();
	}

	@Transactional
	@Override
	public void updatePart(Part om) {
		repo.save(om);
		
	}

	@Transactional
	@Override
	public void deletePart(Integer id) {
		repo.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Part> getOnePart(Integer id) {
		
		return repo.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Part> getAllPart() {
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isPartExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public boolean isPartCodeExist(String partCode) {
		return (repo.getPartCodeCount(partCode))>0?true:false;
	}

	@Override
	public boolean isPartCodeExistForEdit(String partCode, Integer id) {
		return (repo.getPartCodeCountForEdit(partCode,id))>0?true:false;
		
	}

	@Override
	public Map<Integer, String> getPartIdAndCode() {
		return repo.getPartIdAndCode()
				.stream()
				.collect(Collectors.toMap(
						ob->Integer.valueOf(ob[0].toString()), 
						ob->ob[1].toString())
						);
	}

}
