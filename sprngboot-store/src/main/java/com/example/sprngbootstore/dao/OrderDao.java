package com.example.sprngbootstore.dao;

import com.example.sprngbootstore.model.Order;
import com.example.sprngbootstore.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsById(Integer orderId);
    Integer createOrder(Integer userId,Integer total);
    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
