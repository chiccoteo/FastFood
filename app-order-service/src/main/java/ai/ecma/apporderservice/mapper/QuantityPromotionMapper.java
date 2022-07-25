package ai.ecma.apporderservice.mapper;

import ai.ecma.library.entity.QuantityPromotion;
import ai.ecma.library.payload.QuantityPromotionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface QuantityPromotionMapper {

    QuantityPromotionDTO toDTO(QuantityPromotion quantityPromotion);
}
