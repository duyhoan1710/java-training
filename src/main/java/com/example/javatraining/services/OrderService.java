package com.example.javatraining.services;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.order.request.CreateOrderDto;
import com.example.javatraining.dtos.order.request.ListOrderQueryDto;
import com.example.javatraining.dtos.order.response.OrderResponse;

public interface OrderService {
    void createOrder(CreateOrderDto payload);

    ResponsePagination<OrderResponse> getOrders(ListOrderQueryDto query);
}
