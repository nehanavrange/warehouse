package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.model.OrderMethod;
import in.nit.repo.OrderMethodRepository;
import in.nit.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	OrderMethodRepository repo;
	
	@Transactional
	@Override
	public Integer saveOrderMethod(OrderMethod om) {
		
		return repo.save(om).getId();
	}

	@Transactional
	@Override
	public void updateOrderMethod(OrderMethod om) {
		repo.save(om);

	}

	@Transactional
	@Override
	public void deleteOrderMethod(Integer id) {
		repo.deleteById(id);

	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		
		return repo.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<OrderMethod> getAllOrderMethod() {
		
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isOrderMethodExist(Integer id) {
		
		return repo.existsById(id);
	}

}
