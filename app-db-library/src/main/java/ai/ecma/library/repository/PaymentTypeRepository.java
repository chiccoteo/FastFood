package ai.ecma.library.repository;

import ai.ecma.library.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, UUID> {
}
