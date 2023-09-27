package com.desperado.courier.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.desperado.courier.dto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desperado.courier.dto.response.GetCourierMetaInfoResponse;
import com.desperado.courier.entities.courier.Courier;
import com.desperado.courier.dto.CourierDto;
import com.desperado.courier.dto.request.CreateCourierRequest;
import com.desperado.courier.dto.response.CreateCouriersResponse;
import com.desperado.courier.dto.response.GetCouriersResponse;
import com.desperado.courier.entities.courier.CourierType;
import com.desperado.courier.entities.order.Order;
import com.desperado.courier.helper.CountOfWorkingHours;
import com.desperado.courier.mapper.CourierMapper;
import com.desperado.courier.repository.CourierRepository;
import com.desperado.courier.repository.OrderRepository;
import com.desperado.courier.service.CourierService;
import com.desperado.courier.service.OffsetBasedPageRequest;


@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final CourierMapper courierMapper;

    @Transactional(readOnly = true)
    @Override
    public GetCouriersResponse getCouriers(Integer limit, Integer offset) {
        List<Courier> couriers = courierRepository.findAll(new OffsetBasedPageRequest(limit, offset,
                        Sort.by(Sort.Direction.DESC, "id")))
                .getContent();
        List<CourierDto> couriersDto = courierMapper.toDtoList(couriers);
        return new GetCouriersResponse(couriersDto, limit, offset);
    }

    @Override
    @Transactional
    public CreateCouriersResponse createCourier(CreateCourierRequest request) {
        List<CourierDto> couriers = request.getCouriers()
                .stream().map(createCourierDto -> {
                    Courier courier = new Courier();
                    courier.setType(createCourierDto.getType());
                    courier.setRegions(createCourierDto.getRegions());
                    courier.setHours(createCourierDto.getHours());
                    Courier savedCourier = courierRepository.save(courier);
                    return new CourierDto(savedCourier.getId(),
                            savedCourier.getType(),
                            savedCourier.getRegions(),
                            savedCourier.getHours());
                }).collect(Collectors.toList());
        CreateCouriersResponse createCouriersResponse = new CreateCouriersResponse();
        createCouriersResponse.setCouriers(couriers);
        return createCouriersResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public CourierDto getCourierById(Long courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(NotFoundException::new);
        return courierMapper.toDto(courier);
    }

    @Override
    @Transactional(readOnly = true)
    public GetCourierMetaInfoResponse getCourierMetaInfo(Long courierId, String startDate, String endDate) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(NotFoundException::new);

        GetCourierMetaInfoResponse getCourierMetaInfoResponse = courierMapper.toMetaInfo(courier);

        List<Order> orders = orderRepository.findAllBetweenTwoDates(courierId, LocalDate.parse(startDate),
                LocalDate.parse(endDate));
        if (orders.size() > 0) {
            Integer earnings = orders.stream().mapToInt(Order::getCost).sum() * CourierType.getCoefficientOfEarnings(
                    courier.getType()
            );

            long hours = CountOfWorkingHours.getHoursBetweenTwoDates(courier, startDate, endDate);

            Integer rating = Math.round(((float) orders.size() / hours) *
                    CourierType.getCoefficientOfRating(courier.getType()));
            getCourierMetaInfoResponse.setEarnings(earnings);
            getCourierMetaInfoResponse.setRating(rating);
        }
        return getCourierMetaInfoResponse;
    }
}
