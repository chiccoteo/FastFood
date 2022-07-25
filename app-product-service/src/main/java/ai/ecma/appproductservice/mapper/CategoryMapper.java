package ai.ecma.appproductservice.mapper;

import ai.ecma.library.entity.Category;
import ai.ecma.library.payload.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mappings({
            @Mapping(target = "attachmentId", source = "attachment.id"),
            @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    }
    )
    CategoryDTO toDto(Category category);

    List<CategoryDTO> toDto(List<Category> categoryList);

    @Mappings({

            @Mapping(target = "attachment.id", source = "attachmentId"),
            @Mapping(target = "parentCategory.id", source = "parentCategoryId")
    })
    Category toEntity(CategoryDTO categoryDTO);


}
