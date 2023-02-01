package com.example.sprngbootstore.service.impl;

import com.example.sprngbootstore.dao.OrderDao;
import com.example.sprngbootstore.dao.ProductDao;
import com.example.sprngbootstore.dto.BuyItem;
import com.example.sprngbootstore.dto.CreateOrderRequest;
import com.example.sprngbootstore.model.Order;
import com.example.sprngbootstore.model.OrderItem;
import com.example.sprngbootstore.model.Product;
import com.example.sprngbootstore.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        Integer orderId =orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItem(orderId, orderItemList);
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order =orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList =orderDao.getOrderItemsByOrderId(orderId);
    }
}
