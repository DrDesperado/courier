package com.desperado.courier.service.impl;

import com.desperado.courier.dto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import com.desperado.courier.entities.order.Order;
import com.desperado.courier.dto.OrderDto;
import com.desperado.courier.dto.request.CompleteOrderRequestDto;
import com.desperado.courier.dto.request.CreateOrderRequest;
import com.desperado.courier.mapper.OrderMapper;
import com.desperado.courier.repository.CourierRepository;
import com.desperado.courier.repository.OrderRepository;
import com.desperado.courier.service.OffsetBasedPageRequest;
import com.desperado.courier.service.OrderService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrders(Integer limit, Integer offset) {
        return orderMapper.toDtoList(orderRepository.findAll(new OffsetBasedPageRequest(limit, offset,
                Sort.by(Sort.Direction.DESC, "id"))).getContent());
    }

    @Override
    @Transactional
    public List<OrderDto> createOrder(CreateOrderRequest request) {
        return request.getOrders().stream().map(orderDto -> {
                    Order newOrder = new Order();
                    newOrder.setCost(orderDto.getCost());
                    newOrder.setHours(orderDto.getHours());
                    newOrder.setRegions(orderDto.getRegions());
                    newOrder.setWeight(orderDto.getWeight());
                    Order savedOrder = orderRepository.save(newOrder);
                    return orderMapper.toDto(savedOrder);
                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<OrderDto> completeOrder(CompleteOrderRequestDto completeOrderRequestDto) {
        return completeOrderRequestDto.getCompleteOrderList().stream().map(completeOrder -> {
                            Order order = orderRepository.findById(completeOrder.getOrderId())
                                    .orElseThrow(IllegalStateException::new);
                            order.setCourier(courierRepository.findById(completeOrder.getCourierId())
                                    .orElseThrow(IllegalStateException::new));
                            order.setCompletedTime(completeOrder.getCompleteTime());
                            Order updatedOrder = orderRepository.save(order);
                            return orderMapper.toDto(updatedOrder);
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrder(Long orderId) {
        return orderMapper.toDto(orderRepository.findById(orderId)
                .orElseThrow(NotFoundException::new));
    }

}
