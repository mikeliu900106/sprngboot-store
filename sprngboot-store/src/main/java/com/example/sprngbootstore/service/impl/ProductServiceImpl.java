package com.example.sprngbootstore.service.impl;

import com.example.sprngbootstore.constant.ProductCategory;
import com.example.sprngbootstore.dao.ProductDao;
import com.example.sprngbootstore.dto.ProductQueryParams;
import com.example.sprngbootstore.dto.ProductRequest;
import com.example.sprngbootstore.model.Product;
import com.example.sprngbootstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.creatProductId(productRequest);
    }

    public Integer countProducts(ProductQueryParams productQueryParams){
        return productDao.countProducts(productQueryParams);
    }

    @Override
    public void updateProduct(Integer productId,ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
