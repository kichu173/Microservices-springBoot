package com.code.orderservice.service;

import com.code.orderservice.entity.Order;
import com.code.orderservice.external.client.ProductService;
import com.code.orderservice.model.OrderRequest;
import com.code.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> Save the data with the Status Order Created
        //Product Service - Block Products (Reduce the quantity)
        //Payment Service -> Payments -> Success -> COMPLETE, ELSE CANCELLED

        log.info("Placing Order Request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating Order with Status CREATED");

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .build();

        orderRepository.save(order);
        log.info("Order placed Successfully with Order Id: {}", order.getId());

        return order.getId();
    }
}
