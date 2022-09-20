package com.code.orderservice.service;

import com.code.orderservice.model.OrderResponse;
import com.code.orderservice.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
