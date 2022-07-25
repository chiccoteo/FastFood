package ai.ecma.library.repository;

import ai.ecma.library.entity.ParentPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ParentPromotionRepository extends JpaRepository<ParentPromotion, UUID> {

    @Query(nativeQuery = true,value = "select * from parent_promotion pp where active and now() between pp.start_date and pp.end_date limit 1")
    Optional<ParentPromotion> getCurrentParentPromotion();
}
