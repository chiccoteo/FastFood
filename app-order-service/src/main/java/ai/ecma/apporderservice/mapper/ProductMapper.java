package ai.ecma.apporderservice.mapper;


import ai.ecma.library.entity.Product;
import ai.ecma.library.payload.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "discountId", source = "discount.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "attachmentId", source = "attachment.id")
    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTO(List<Product> products);

}
