package com.desperado.courier.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.desperado.courier.dto.CourierDto;
import com.desperado.courier.dto.create.CreateCourierDto;

import java.util.List;

public class CreateCourierRequest {

    @NotNull
    @Valid
    private List<CreateCourierDto> couriers;

    public List<CreateCourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CreateCourierDto> couriers) {
        this.couriers = couriers;
    }
}
