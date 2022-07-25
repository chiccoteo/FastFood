package ai.ecma.library.repository;

import ai.ecma.library.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByParentCategoryIsNull(Sort by);

    List<Category> findByParentCategoryId(UUID parentCategory_id);

}
