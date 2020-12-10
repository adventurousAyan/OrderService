package com.example.demo.service.impl;

import com.example.demo.clientfactory.OrderItemClient;
import com.example.demo.dto.OrderItem;
import com.example.demo.entity.Order;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.CustomResponse;
import com.example.demo.service.IOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * <p>Order Service for creating/retrieving orders</p>
 */
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemClient orderItemClient;

    @Autowired
    private ObjectMapper mapper;

    /**
     * Create Order
     *
     * @param orderInfo
     * @return
     */
    public boolean createOrder(Order orderInfo) {
            Order newOrder = orderRepository.save(orderInfo);
            orderInfo.getOrderItems().forEach(x -> {
                x.setOrderId(newOrder.getId());
            });
            CustomResponse res = orderItemClient.createOrderItems(orderInfo.getOrderItems());
            return res.getStatus() == HttpStatus.CREATED;
    }

    /**
     * Retrieves all orders
     *
     * @return
     */
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.size() == 0) {
            throw new OrderNotFoundException("Orders not found");
        }
        for (Order order : orderList) {
            CustomResponse res = orderItemClient.getOrderItems(order.getId());
            if (res.getStatus() == HttpStatus.OK) {
                List<OrderItem> orderItems = mapper.convertValue(
                        res.getData(),
                        new TypeReference<List<OrderItem>>() {
                        });
                double totalCost = orderItems.stream().mapToDouble(x -> x.getQuantity() * x.getPerItemCost()).sum();
                order.setTotal(totalCost);
                order.setOrderItems(orderItems);
            }
        }
        return orderList;
    }

    /**
     * Get order details by order-id
     *
     * @param id
     * @return
     */
    public Order getOrderByOrderId(long id) {
        double totalCost;
        Order order = orderRepository.findOderInfoById(id);
        if (order == null) {
            throw new OrderNotFoundException("Order details not found for order id - " + id);
        }
        CustomResponse res = orderItemClient.getOrderItems(order.getId());
        if (res.getStatus() == HttpStatus.OK) {
            List<OrderItem> orderItems = mapper.convertValue(
                    res.getData(),
                    new TypeReference<List<OrderItem>>() {
                    });
            totalCost = orderItems.stream().mapToDouble(x -> x.getQuantity() * x.getPerItemCost()).sum();
            order.setOrderItems(orderItems);
            order.setTotal(totalCost);
        }
        return order;

    }
}

