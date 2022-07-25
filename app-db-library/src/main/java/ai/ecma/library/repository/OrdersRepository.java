package ai.ecma.library.repository;

import ai.ecma.library.entity.Order;
import ai.ecma.library.enums.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Order, UUID> {
    boolean existsBySerialNumber(String number);
    @Query(value = "select sum(case when status='SUCCESS' then 1 else 0 end)\n" +
            "           >sum(case when status='CANCEL' then 1 else 0 end)\n" +
            " from orders where client_id=:clientId", nativeQuery = true)
    boolean isReliable( UUID clientId);

    List<Order> findAllByStatus(OrderStatus status, Pageable pageable);
}