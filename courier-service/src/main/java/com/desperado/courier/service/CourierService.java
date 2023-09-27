package com.desperado.courier.service;

import com.desperado.courier.dto.CourierDto;
import com.desperado.courier.dto.request.CreateCourierRequest;
import com.desperado.courier.dto.response.CreateCouriersResponse;
import com.desperado.courier.dto.response.GetCourierMetaInfoResponse;
import com.desperado.courier.dto.response.GetCouriersResponse;


public interface CourierService {
    GetCouriersResponse getCouriers(Integer limit, Integer offset);
    //CreateCourierRequest, CreateCourierResponse
    CreateCouriersResponse createCourier(CreateCourierRequest createCourierRequest);
    CourierDto getCourierById(Long courierId);

    GetCourierMetaInfoResponse getCourierMetaInfo(Long courierId, String startDate, String endDate);
}
