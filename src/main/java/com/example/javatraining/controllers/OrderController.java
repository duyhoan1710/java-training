package com.example.javatraining.controllers;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.order.request.CreateOrderDto;
import com.example.javatraining.dtos.order.request.ListOrderQueryDto;
import com.example.javatraining.dtos.order.response.OrderResponse;
import com.example.javatraining.services.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @PostMapping("orders")
    void CreateOrder(@RequestBody CreateOrderDto payload) {
        orderService.createOrder(payload);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @GetMapping("orders")
    ResponsePagination<OrderResponse> getOrders(@RequestParam(required = true) int page, @RequestParam(required = true) int limit) {
        ListOrderQueryDto listOrderQueryDto = new ListOrderQueryDto();
        listOrderQueryDto.setPage(page);
        listOrderQueryDto.setLimit(limit);

        return orderService.getOrders(listOrderQueryDto);
    }

}
