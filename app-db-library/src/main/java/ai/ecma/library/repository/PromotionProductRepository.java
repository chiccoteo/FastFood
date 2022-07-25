package ai.ecma.library.repository;

import ai.ecma.library.entity.PromotionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PromotionProductRepository extends JpaRepository<PromotionProduct, UUID> {

    @Query(value = "select *\n" +
            "from promotion_product prp\n" +
            "         join product_promotion pp on pp.id = prp.product_promotion_id\n" +
            "         join parent_promotion on pp.parent_promotion_id = parent_promotion_id\n" +
            "where pp.price <= :totalSumma",nativeQuery = true)
    List<PromotionProduct> getPromotionProductByProductPromotionPrice(double totalSumma);
}
