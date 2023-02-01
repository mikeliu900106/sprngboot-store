package com.example.sprngbootstore.service;

import com.example.sprngbootstore.constant.ProductCategory;
import com.example.sprngbootstore.dto.ProductQueryParams;
import com.example.sprngbootstore.dto.ProductRequest;
import com.example.sprngbootstore.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    Integer countProducts(ProductQueryParams productQueryParams);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
