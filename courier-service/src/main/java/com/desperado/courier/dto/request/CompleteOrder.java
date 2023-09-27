package com.desperado.courier.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CompleteOrder {
    @JsonProperty(value = "complete_time")
    @NotNull
    private LocalDateTime completeTime;
    @JsonProperty(value = "courier_id")
    @NotNull
    private Long courierId;
    @JsonProperty(value = "order_id")
    @NotNull
    private Long orderId;
}
