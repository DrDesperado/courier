package com.desperado.courier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desperado.courier.entities.order.Order;
import com.desperado.courier.dto.OrderDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    @Mapping(ignore = true, target = "courier")
    Order toEntity(OrderDto orderDto);
    List<OrderDto> toDtoList(List<Order> orders);
    List<Order> toEntityList(List<OrderDto> orderDtoList);
}
