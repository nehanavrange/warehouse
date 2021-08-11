package in.nit.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)
	@Override
	public Optional<WhUserType> getOneWhUserType(Integer id) {
		
		return repo.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<WhUserType> getAllWhUserType() {
		
		return repo.findAll();
	}

	@Transactional
	@Override
	public boolean isWhUserTypeExist(Integer id) {
		
		return repo.existsById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isWhUserTypeEmailExist(String mail) {
		
		return repo.getWhUserTypeMailCount(mail)>0 ? true:false;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Integer, String> getWhUserTypeIdAndCode(String userType) {
		
		return repo.getWhUserTypeIdAndCode(userType).
				stream().collect(Collectors.
						toMap(ob->Integer.valueOf(ob[0].toString()), ob->ob[1].toString()));
	}

}
