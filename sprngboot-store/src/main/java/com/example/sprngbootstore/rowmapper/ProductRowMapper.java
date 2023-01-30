package com.example.sprngbootstore.rowmapper;

import com.example.sprngbootstore.constant.ProductCategory;
import com.example.sprngbootstore.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));

        String caegoryStr = rs.getString("category");
        ProductCategory category =  ProductCategory.valueOf(caegoryStr);
        product.setCategory(category);


        product.setImage_url(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreated_date(rs.getDate("created_date"));
        product.setLast_modified_date(rs.getDate("last_modified_date"));
        System.out.println(product);
        return product;

    }
}
