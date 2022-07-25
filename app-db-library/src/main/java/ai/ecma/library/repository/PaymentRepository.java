package ai.ecma.library.repository;

import ai.ecma.library.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

        Optional<Payment> findByOrderId(UUID order_id);
}
