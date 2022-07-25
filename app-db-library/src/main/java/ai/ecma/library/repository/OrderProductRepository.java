package ai.ecma.library.repository;

import ai.ecma.library.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findByOrderId(UUID order_id);
}
