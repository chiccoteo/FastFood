package ai.ecma.library.repository;

import ai.ecma.library.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(UUID category_id);
}
