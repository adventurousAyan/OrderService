package com.example.demo.service;

import com.example.demo.entity.Order;

import java.util.List;

public interface IOrderService {

    /**
     * Create an order
     * @param orderInfo
     * @return
     */
    boolean createOrder(Order orderInfo);

    /**
     * Gets an order by order-id
     * @param id
     * @return
     */
    Order getOrderByOrderId(long id);

    /**
     * Retrieves all orders
     * @return
     */
    List<Order> getAllOrders();
}
