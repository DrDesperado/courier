package com.desperado.courier.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

import com.desperado.courier.entities.courier.CourierType;


@Data
@JsonPropertyOrder({"courier_id", "courier_type", "regions", "working_hours", "rating", "earnings"})
public class GetCourierMetaInfoResponse {
    @JsonProperty(value = "courier_id")
    @NotNull
    private Long id;
    @JsonProperty(value = "courier_type")
    @NotNull
    private CourierType type;
    @NotNull
    private List<Integer> regions;
    @JsonProperty(value = "working_hours")
    @NotNull
    private List<@Pattern(regexp = "^(2[0-3]|[01][0-9]):[0-5][0-9]-(2[0-3]|[01][0-9]):[0-5][0-9]$")
            String> hours;
    @NotNull
    private Integer rating = 0;
    @NotNull
    private Integer earnings = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getEarnings() {
        return earnings;
    }

    public void setEarnings(Integer earnings) {
        this.earnings = earnings;
    }
}
