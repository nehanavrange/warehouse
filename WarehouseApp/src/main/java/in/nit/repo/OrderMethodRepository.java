package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

}
