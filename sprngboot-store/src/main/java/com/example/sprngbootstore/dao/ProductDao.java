package com.example.sprngbootstore.dao;

import com.example.sprngbootstore.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao {
    Product getById(Integer productId);


}
