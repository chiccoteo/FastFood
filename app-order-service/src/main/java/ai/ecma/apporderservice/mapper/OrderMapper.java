package ai.ecma.apporderservice.mapper;

import ai.ecma.apporderservice.payload.OrderDTO;
import ai.ecma.library.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses =OrderProductMapper.class)
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTO(List<Order> orders);
}
