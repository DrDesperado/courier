package com.desperado.courier.controller;

import com.desperado.courier.dto.response.BadRequestResponse;
import com.desperado.courier.dto.response.NotFoundResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.desperado.courier.dto.OrderDto;
import com.desperado.courier.dto.request.CompleteOrderRequestDto;
import com.desperado.courier.dto.request.CreateOrderRequest;
import com.desperado.courier.service.OrderService;



@RestController
@RequestMapping("/courier/orders")
@RequiredArgsConstructor
@Validated
@ApiResponse(responseCode = "400", description = "bad request", content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = BadRequestResponse.class)))
public class OrderController {
    private final OrderService orderService;
    @GetMapping
//    @RolesAllowed({"USER"})
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(defaultValue = "1") Integer limit,
                                                    @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(orderService.getOrders(limit, offset));
    }

    @PostMapping
    public ResponseEntity<List<OrderDto>> createOrder(@Validated @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{order_id}")
    @ApiResponse(responseCode = "404", description = "not found", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = NotFoundResponse.class)))
    public ResponseEntity<OrderDto> getOrder(@PathVariable(value = "order_id") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/complete")
    public ResponseEntity<List<OrderDto>> completeOrder(@Validated @RequestBody CompleteOrderRequestDto completeOrderRequestDto) {
        return ResponseEntity.ok(orderService.completeOrder(completeOrderRequestDto));
    }

}
