package com.example.sprngbootstore.dao.impl;

import com.example.sprngbootstore.dao.OrderDao;
import com.example.sprngbootstore.model.Order;
import com.example.sprngbootstore.model.OrderItem;
import com.example.sprngbootstore.rowmapper.OrderItemRowMapper;
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
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM java_store.order WHERE order_id=:order_id";
        Map<String,Object> map = new HashMap<>();
        map.put("order_id",orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());
        if(orderList.size()>0){
            return  orderList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsById(Integer orderId) {
        String sql ="SELECT oi.order_item_id ,oi.order_id,oi.product_id,oi.quantity,oi.amount,p.product_name,p.image_url FROM order_item as oi LEFT JOIN product  as p ON oi.product_id = p.product_id WHERE oi.order_id = :order_id";
        Map<String,Object> map =new HashMap<>();
        map.put("order_id",orderId);
        List<OrderItem> orderItemList =namedParameterJdbcTemplate.query(sql,map,new OrderItemRowMapper());
        return orderItemList;
    }

    @Override
    public Integer createOrder(Integer userId, Integer total) {

        String sql = "INSERT INTO java_store.order(user_id,total_amount,created_date,last_modified_date)" +
                "values(:user_id,:total_amount,:created_date,:last_modified_date)";

        Map<String,Object> map = new HashMap<>();
        Date now = new Date();
        map.put("user_id",userId);
        map.put("total_amount",total);
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int orderId = keyHolder.getKey().intValue();
        return  orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO java_store.order_item(order_id,product_id,quantity,amount)" +
                "values(:order_id,:product_id,:quantity,:amount)";
        MapSqlParameterSource[] mapSqlParameterSources =new MapSqlParameterSource[orderItemList.size()];
        for (int i = 0; i < orderItemList.size();i++){
            OrderItem orderItem =orderItemList.get(i);
            mapSqlParameterSources[i] = new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("order_id",orderId);
            mapSqlParameterSources[i].addValue("product_id",orderItem.getProductId());
            mapSqlParameterSources[i].addValue("quantity",orderItem.getQuantity());
            mapSqlParameterSources[i].addValue("amount",orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql,mapSqlParameterSources);

    }
}
