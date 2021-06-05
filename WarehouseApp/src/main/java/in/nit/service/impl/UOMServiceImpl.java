package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.model.UOMType;
import in.nit.repo.UOMTypeRepository;
import in.nit.service.IUOMService;

@Service
public class UOMServiceImpl implements IUOMService {

	@Autowired
	private UOMTypeRepository repo;
	
	@Transactional
	@Override
	public Integer saveUOMType(UOMType st) {
		Integer id=repo.save(st).getId();
		return id;
	}
	
	@Transactional
	@Override
	public void updateUOMType(UOMType st) {
		repo.save(st);
	}
	
	@Transactional
	@Override
	public void deleteUOMType(Integer id) {
		repo.deleteById(id);;

	}
	@Transactional(readOnly = true)
	@Override
	public Optional<UOMType> getOneUOMType(Integer id) {
		Optional<UOMType>opt=repo.findById(id);
		return opt;
	}
	@Transactional(readOnly = true)
	@Override
	public List<UOMType> getAllUOMType() {
		List<UOMType> list=repo.findAll();
		return list;
	}
	@Transactional(readOnly = true)
	@Override
	public boolean isUOMTypeExist(Integer id) {
		boolean flag=repo.existsById(id);
		return flag;
	}

}
