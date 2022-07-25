package ai.ecma.library.repository;

import ai.ecma.library.entity.QuantityPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuantityPromotionRepository extends JpaRepository<QuantityPromotion, UUID> {

    Optional<QuantityPromotion> findByParentPromotionId(UUID parentPromotion_id);
}
