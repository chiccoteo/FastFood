package ai.ecma.apporderservice.mapper;

import ai.ecma.library.entity.OrderProduct;
import ai.ecma.library.payload.OrderProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "orderId", source = "order.id")
    OrderProductDTO toDTO(OrderProduct orderProduct);

    List<OrderProductDTO> toDTO(List<OrderProduct> orderProductList);
}
