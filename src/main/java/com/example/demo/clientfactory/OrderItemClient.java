package com.example.demo.clientfactory;


import com.example.demo.dto.OrderItem;
import com.example.demo.response.CustomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url="http://localhost:8090", name="ORDER-ITEM-CLIENT")
public interface OrderItemClient {

    /**
     * Gets all order details
     * @return
     */
    @GetMapping("/orderItems/{orderId}")
    CustomResponse getOrderItems(@PathVariable("orderId")long orderId);

    /**
     * Creates a new order
     * @param orderItems
     * @return
     */
    @PostMapping("/orderItems")
    CustomResponse createOrderItems(List<OrderItem> orderItems);
}
