package com.example.demo;

import com.example.demo.dto.OrderItem;
import com.example.demo.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class MockObjects {

    public static Order getOrder(){
        Order order = new Order();
        order.setCustomerName("Ayan");
        order.setOrderDate("2020-10-12");
        order.setId((long) 1);
        OrderItem item = new OrderItem();
        item.setPerItemCost(20);
        item.setProductCode("PA3");
        item.setProductName("Shampoo");
        item.setQuantity(1);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(item);
        order.setOrderItems(orderItemList);
        return order;
    }

    public static List<OrderItem> getOrderItems(){
        OrderItem item = new OrderItem();
        item.setPerItemCost(20);
        item.setProductCode("PA3");
        item.setProductName("Shampoo");
        item.setQuantity(1);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(item);
        return orderItemList;
    }
}
