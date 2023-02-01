package com.example.sprngbootstore.dao;

import com.example.sprngbootstore.constant.ProductCategory;
import com.example.sprngbootstore.dto.ProductQueryParams;
import com.example.sprngbootstore.dto.ProductRequest;
import com.example.sprngbootstore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer creatProductId(ProductRequest productRequest);
    Integer countProducts(ProductQueryParams productQueryParams);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
