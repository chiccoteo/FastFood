package ai.ecma.library.repository;

import ai.ecma.library.entity.ParentPromotion;
import ai.ecma.library.entity.PercentPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PercentPromotionRepository extends JpaRepository<PercentPromotion, UUID> {

    Optional<PercentPromotion> findByParentPromotionAndPriceLessThanEqual(ParentPromotion parentPromotion, double price);
}
