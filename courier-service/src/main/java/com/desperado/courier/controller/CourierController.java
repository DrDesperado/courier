package com.desperado.courier.controller;

import com.desperado.courier.dto.CourierDto;

import com.desperado.courier.dto.response.BadRequestResponse;
import com.desperado.courier.dto.response.NotFoundResponse;
import com.desperado.courier.dto.request.CreateCourierRequest;
import com.desperado.courier.dto.response.CreateCouriersResponse;
import com.desperado.courier.dto.response.GetCourierMetaInfoResponse;
import com.desperado.courier.dto.response.GetCouriersResponse;

import com.desperado.courier.service.impl.CourierServiceImpl;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier/couriers")
@Validated
@RequiredArgsConstructor
@ApiResponse(responseCode = "400", description = "bad request", content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = BadRequestResponse.class)))
public class CourierController {
    private final CourierServiceImpl courierService;

    @GetMapping
//    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetCouriersResponse> getCouriers(@RequestParam(defaultValue = "1") Integer limit,
                                                           @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(courierService.getCouriers(limit, offset));
    }

    @PostMapping
    public ResponseEntity<CreateCouriersResponse> createCouriers(@Validated @RequestBody CreateCourierRequest request) {
        return ResponseEntity.ok(courierService.createCourier(request));
    }

    @GetMapping("/{courier_id}")
    @ApiResponse(responseCode = "404", description = "not found", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = NotFoundResponse.class)))
    public ResponseEntity<CourierDto> getCouriers(@PathVariable(name = "courier_id") Long courierId) {
        return ResponseEntity.ok(courierService.getCourierById(courierId));
    }

    @GetMapping("/meta-info/{courier_id}")
    public ResponseEntity<GetCourierMetaInfoResponse> getCourierMetaInfo(@PathVariable(name = "courier_id")
                                                                         Long courierId,
                                                                         @RequestParam String startDate,
                                                                         @RequestParam String endDate) {
        return ResponseEntity.ok(courierService.getCourierMetaInfo(courierId, startDate, endDate));
    }

}


