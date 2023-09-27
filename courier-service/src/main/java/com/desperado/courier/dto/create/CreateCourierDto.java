package com.desperado.courier.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.desperado.courier.entities.courier.CourierType;

import java.util.List;

public class CreateCourierDto {
    @JsonProperty(value = "courier_type")
    @NotNull
    private CourierType type;

    @NotNull
    private List<Integer> regions;
    @JsonProperty(value = "working_hours")
    @NotNull
    private List<@Pattern(regexp = "^(2[0-3]|[01][0-9]):[0-5][0-9]-(2[0-3]|[01][0-9]):[0-5][0-9]$")
            String> hours;

    public CourierType getType() {
        return type;
    }

    public void setType(CourierType type) {
        this.type = type;
    }

    public List<Integer> getRegions() {
        return regions;
    }

    public void setRegions(List<Integer> regions) {
        this.regions = regions;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }
}
