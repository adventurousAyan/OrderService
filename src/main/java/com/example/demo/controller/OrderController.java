package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.response.CustomResponse;
import com.example.demo.service.IOrderService;
import com.example.demo.util.TWConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Order Service Controller Class
 */
@RestController
@Api(value = "Order Service")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Create order request", notes = "Create a order request",
            response = CustomResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = TWConstants.API_RESPONSE_201),
            @ApiResponse(code = 401, message = TWConstants.API_RESPONSE_401),
            @ApiResponse(code = 403, message = TWConstants.API_RESPONSE_403),
            @ApiResponse(code = 404, message = TWConstants.API_RESPONSE_404),
            @ApiResponse(code = 500, message = TWConstants.API_RESPONSE_500),
    })
    public CustomResponse addOrder(@Valid @RequestBody Order order) {
       boolean res = orderService.createOrder(order);
       if(res)
           return new CustomResponse(HttpStatus.CREATED, "Order created successfully");
       else
           return new CustomResponse(HttpStatus.BAD_REQUEST, "Unable to create order. Please try again");
    }

    @GetMapping(value = "/orders")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Gets all orders along with order items", notes = "Gets all orders along with order items",
            response = CustomResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = TWConstants.API_RESPONSE_200),
            @ApiResponse(code = 401, message = TWConstants.API_RESPONSE_401),
            @ApiResponse(code = 403, message = TWConstants.API_RESPONSE_403),
            @ApiResponse(code = 404, message = TWConstants.API_RESPONSE_404),
            @ApiResponse(code = 500, message = TWConstants.API_RESPONSE_500),
    })
    public CustomResponse getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if(orders.size() > 0)
            return new CustomResponse(HttpStatus.OK, "Order fetched successfully");
        else
            return new CustomResponse(HttpStatus.BAD_REQUEST, "Unable to fetch orders. Please try again");
    }

    @GetMapping(value = "/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Gets order info by order id", notes = "Gets order info by order id",
            response = CustomResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = TWConstants.API_RESPONSE_200),
            @ApiResponse(code = 401, message = TWConstants.API_RESPONSE_401),
            @ApiResponse(code = 403, message = TWConstants.API_RESPONSE_403),
            @ApiResponse(code = 404, message = TWConstants.API_RESPONSE_404),
            @ApiResponse(code = 500, message = TWConstants.API_RESPONSE_500),
    })
    public CustomResponse getOrderByOrderId(@PathVariable int id) {
        Order order = orderService.getOrderByOrderId(id);
        if(order.getId() > 0)
            return new CustomResponse(HttpStatus.OK, "Order fetched successfully for order id :- " + id);
        else
            return new CustomResponse(HttpStatus.BAD_REQUEST, "Unable to fetch orders. Please try again");
    }
}
