package ai.ecma.library.repository;

import ai.ecma.library.entity.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductPromotionRepository extends JpaRepository<ProductPromotion, UUID> {
}
