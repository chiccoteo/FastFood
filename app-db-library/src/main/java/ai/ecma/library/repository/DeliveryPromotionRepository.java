package ai.ecma.library.repository;

import ai.ecma.library.entity.DeliveryPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DeliveryPromotionRepository extends JpaRepository<DeliveryPromotion, UUID> {

    @Query(value = "select *\n" +
            "from delivery_promotion dp\n" +
            "where parent_promotion_id = :parentPromotionId\n" +
            "  and current_time at time zone 'Asia/Tashkent' between dp.start_time and dp.end_time", nativeQuery = true)
    boolean existsActive();
}
