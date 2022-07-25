package ai.ecma.apporderservice.mapper;

import ai.ecma.library.entity.PromotionProduct;
import ai.ecma.library.payload.PromotionProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionProductMapper {
    PromotionProductDTO toDTO(PromotionProduct promotionProduct);

    List<PromotionProductDTO> toDTO(List<PromotionProduct> promotionProductList);
}
