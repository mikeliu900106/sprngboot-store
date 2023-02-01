package com.example.sprngbootstore.service;

import com.example.sprngbootstore.dto.CreateOrderRequest;
import com.example.sprngbootstore.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}