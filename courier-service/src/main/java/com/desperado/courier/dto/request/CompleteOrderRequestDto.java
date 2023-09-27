package com.desperado.courier.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompleteOrderRequestDto {
    @JsonProperty(value = "complete_info")
    @NotNull
    @Valid
    private List<CompleteOrder> completeOrderList;

    public List<CompleteOrder> getCompleteOrderList() {
        return completeOrderList;
    }

    public void setCompleteOrderList(List<CompleteOrder> completeOrderList) {
        this.completeOrderList = completeOrderList;
    }
}
