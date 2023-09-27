package com.desperado.courier.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import com.desperado.courier.dto.CourierDto;

public class CreateCouriersResponse {
    @NotNull
    @Valid
    private List<CourierDto> couriers;

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CourierDto> couriers) {
        this.couriers = couriers;
    }
}
