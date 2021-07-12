package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

	@Query("SELECT COUNT(OM.orderCode) FROM OrderMethod OM WHERE OM.orderCode=:orderCode")
	public Integer getOrderMethodCodeCount(String orderCode);
}
