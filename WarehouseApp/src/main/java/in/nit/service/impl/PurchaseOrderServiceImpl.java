package in.nit.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.model.PurchaseOrder;
import in.nit.repo.PurchaseOrderRepository;
import in.nit.service.IPurchaseOrderService;


@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	
	@Autowired
	private PurchaseOrderRepository repo;
	
	
	@Transactional()
	@Override
	public Integer savePurchaseOrder(PurchaseOrder order) {
		
		return repo.save(order).getId();
	}

	@Transactional()
	@Override
	public void updatePurchaseOrder(PurchaseOrder order) {
		repo.save(order);

	}

	@Transactional()
	@Override
	public void deletePurchaseOrder(Integer id) {
		repo.deleteById(id);

	}

	@Transactional(readOnly = true)
	@Override
	public Optional<PurchaseOrder> getOnePurchaseOrder(Integer id) {
		return repo.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

	
	@Transactional(readOnly = true)
	@Override
	public boolean isPurchaseOrderExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isPurchaseOrderCodeExist(String orderCode) {
		return repo.getPurchaseOrderCodeCount(orderCode)>0?true:false;
	}

	@Override
	public List<Object[]> getPurchaseOrderQualityCheckCount() {
		return repo.getPurchaseOrderQualityCheckCount();
	}

}
