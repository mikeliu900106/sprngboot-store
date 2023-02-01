package com.example.sprngbootstore.dao.impl;

import com.example.sprngbootstore.constant.ProductCategory;
import com.example.sprngbootstore.dao.ProductDao;
import com.example.sprngbootstore.dto.ProductQueryParams;
import com.example.sprngbootstore.dto.ProductRequest;
import com.example.sprngbootstore.model.Product;
import com.example.sprngbootstore.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String addFilteringSql(String sql,Map<String,Object> map,ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category ";
            map.put("category",productQueryParams.getCategory().name());
        }
        if(productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search ";
            map.put("search","%"+productQueryParams.getSearch()+"%");
        }
        return sql;
    }
    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id ,product_name,category,image_url,price,stock," +
                     "description,created_date,last_modified_date From java_store.product WHERE 1 = 1" ;
        Map<String,Object> map = new HashMap();

        sql = addFilteringSql(sql,map,productQueryParams);

        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset ";

        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {


        String sql = "SELECT product_id ,product_name,category,image_url,price,stock,description,created_date,last_modified_date From java_store.product where product_id = :productId";
        Map<String,Object> map = new HashMap();
        map.put("productId",productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        if(productList.size() > 0){
            return productList.get(0);
        }else{
            System.out.println("service有錯");
            return null;

        }

    }

    @Override
    public Integer creatProductId(ProductRequest productRequest) {
        String sql = "INSERT INTO java_store.product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)\n" +
                "values (:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";
        Map<String,Object> map = new HashMap<>();
        map.put("product_name",productRequest.getProduct_name());
        System.out.println(productRequest.getCategory());
        map.put("category",productRequest.getCategory().toString());
        map.put("image_url",productRequest.getImage_url());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;
    }
    public Integer countProducts(ProductQueryParams productQueryParams){
        String sql = "SELECT count(*) From java_store.product Where 1=1";

        Map<String,Object> map = new HashMap();

        sql = addFilteringSql(sql,map,productQueryParams);

        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset ";

        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        Integer total  = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
        return total;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE java_store.product SET  product_name=:product_name," +
                     "category =:category,image_url=:image_url,price=:price,stock=:stock ,description =:description,last_modified_date =:last_modified_date WHERE product_id = :product_id";
        Map<String,Object> map = new HashMap<>();
        Date now = new Date();

        map.put("product_id",productId);
        map.put("product_name",productRequest.getProduct_name());
        System.out.println(productRequest.getCategory());
        map.put("category",productRequest.getCategory().toString());
        map.put("image_url",productRequest.getImage_url());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("last_modified_date",now);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM java_store.product WHERE product_id = :product_id";
        Map<String,Object> map = new HashMap<>();
        map.put("product_id",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }


}
