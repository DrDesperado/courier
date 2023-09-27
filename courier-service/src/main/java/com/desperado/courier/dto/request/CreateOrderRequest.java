package com.desperado.courier.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.desperado.courier.dto.create.CreateOrderDto;

import java.util.List;

public class CreateOrderRequest {
    @NotNull
    @Valid
    private List<CreateOrderDto> orders;

    public List<CreateOrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<CreateOrderDto> orders) {
        this.orders = orders;
    }
}
