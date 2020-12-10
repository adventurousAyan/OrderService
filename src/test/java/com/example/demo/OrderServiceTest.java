package com.example.demo;

import com.example.demo.clientfactory.OrderItemClient;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.CustomResponse;
import com.example.demo.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderItemClient orderItemClient;

    @Test
    public void test_createOrder() {
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(MockObjects.getOrder());
        Mockito.when(orderItemClient.createOrderItems(Mockito.any()))
                .thenReturn(new CustomResponse(HttpStatus.CREATED, "Test"));
        boolean res = orderService.createOrder(MockObjects.getOrder());
        assertThat(res).isEqualTo(true);

    }

    @Test
    public void test_NotCreateOrder() {
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(MockObjects.getOrder());
        Mockito.when(orderItemClient.createOrderItems(Mockito.any()))
                .thenReturn(new CustomResponse(HttpStatus.BAD_REQUEST, "Test"));
        boolean res = orderService.createOrder(MockObjects.getOrder());
        assertThat(res).isEqualTo(false);

    }

    @Test
    public void test_getAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(MockObjects.getOrder()));
        Mockito.when(orderItemClient.getOrderItems(Mockito.anyLong()))
                .thenReturn(new CustomResponse(HttpStatus.CREATED, MockObjects.getOrderItems()));
         List<Order> orders = orderService.getAllOrders();
        assertThat(orders.size()).isGreaterThan(0);

    }

    @Test
    public void test_getOrderByOrderId() {
        Mockito.when(orderRepository.findOderInfoById(Mockito.anyLong())).thenReturn(MockObjects.getOrder());
        Mockito.when(orderItemClient.getOrderItems(Mockito.anyLong()))
                .thenReturn(new CustomResponse(HttpStatus.CREATED, MockObjects.getOrderItems()));
        Order order = orderService.getOrderByOrderId(1);
        assertThat(order.getId()).isEqualTo(1);

    }

}
