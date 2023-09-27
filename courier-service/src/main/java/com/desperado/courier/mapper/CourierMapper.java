package com.desperado.courier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desperado.courier.dto.response.GetCourierMetaInfoResponse;
import com.desperado.courier.entities.courier.Courier;
import com.desperado.courier.dto.CourierDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    CourierDto toDto(Courier courier);

    @Mapping(ignore = true, target = "orders")
    Courier toEntity(CourierDto courierDto);

    @Mapping(ignore = true, target = "rating")
    @Mapping(ignore = true, target = "earnings")
    GetCourierMetaInfoResponse toMetaInfo(Courier courier);
    List<CourierDto> toDtoList(List<Courier> couriers);
    List<Courier> toEntityList(List<CourierDto> couriersDto);
}
