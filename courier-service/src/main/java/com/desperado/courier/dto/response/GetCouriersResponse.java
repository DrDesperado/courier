package com.desperado.courier.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.desperado.courier.dto.CourierDto;

@Data
@AllArgsConstructor
public class GetCouriersResponse {
    @NotNull
    @Valid
    private List<CourierDto> couriers;
    @NotNull
    private Integer limit;
    @NotNull
    private Integer offset;
}
