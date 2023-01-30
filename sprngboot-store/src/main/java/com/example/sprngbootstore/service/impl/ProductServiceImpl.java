package com.example.sprngbootstore.service.impl;

import com.example.sprngbootstore.dao.ProductDao;
import com.example.sprngbootstore.model.Product;
import com.example.sprngbootstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getById(Integer productId) {
        return productDao.getById(productId);
    }
}
