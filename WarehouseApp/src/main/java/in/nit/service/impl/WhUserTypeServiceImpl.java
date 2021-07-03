package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.WhUserType;
import in.nit.repo.WhUserTypeRepository;
import in.nit.service.IWhUserTypeService;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService{

	@Autowired
	private WhUserTypeRepository repo;
	
	@Transactional
	@Override
	public Integer saveWhUserType(WhUserType user) {
		
		return repo.save(user).getId() ;
	}
	
	@Transactional
	@Override
	public void updateWhUserType(WhUserType user) {
		repo.save(user);
	}

	@Transactional
	@Override
	public void deleteWhUserType(Integer id) {
		repo.deleteById(id);
	}

	@Transactional
	@Override
	public Optional<WhUserType> getOneWhUserType(Integer id) {
		
		return repo.findById(id);
	}

	@Transactional
	@Override
	public List<WhUserType> getAllWhUserType() {
		
		return repo.findAll();
	}

	@Transactional
	@Override
	public boolean isWhUserTypeExist(Integer id) {
		
		return repo.existsById(id);
	}

}
