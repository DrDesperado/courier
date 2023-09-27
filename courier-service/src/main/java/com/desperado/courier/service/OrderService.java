package com.desperado.courier.service;


import com.desperado.courier.dto.OrderDto;
import com.desperado.courier.dto.request.CompleteOrderRequestDto;
import com.desperado.courier.dto.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders(Integer limit, Integer offset);
    List<OrderDto> createOrder(CreateOrderRequest createOrderRequest); //CreateOrderRequest

    //Complete Order Request DTO
    List<OrderDto> completeOrder(CompleteOrderRequestDto completeOrderRequestDto);
    OrderDto getOrder(Long orderId);

}
