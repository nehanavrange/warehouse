package in.nit.service;



import java.util.List;
import java.util.Optional;

import in.nit.model.PurchaseOrder;

public interface IPurchaseOrderService {

	Integer savePurchaseOrder(PurchaseOrder order);
	void updatePurchaseOrder(PurchaseOrder order);
	void deletePurchaseOrder(Integer id);
	Optional<PurchaseOrder> getOnePurchaseOrder(Integer id);
	List<PurchaseOrder> getAllPurchaseOrders();
	boolean isPurchaseOrderExist(Integer id);

	boolean isPurchaseOrderCodeExist(String orderCode); 
	List<Object[]>getPurchaseOrderQualityCheckCount();


}
