package com.example.demo;

import com.example.demo.controller.OrderController;
import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @MockBean
    IOrderService orderService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private Order orderInfo;

    @Test
    public void test_createOrder() throws Exception {
        when(orderService.createOrder(Mockito.any())).
                thenReturn(true);
        String json = mapper.writeValueAsString(MockObjects.getOrder());
        MockHttpServletResponse res = mockMvc.perform(MockMvcRequestBuilders
                .post("/order")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn()
                .getResponse();
        assertThat(res.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void test_getOrders() throws Exception {
        when(orderService.getAllOrders()).
                thenReturn(Arrays.asList(MockObjects.getOrder()));
        String json = mapper.writeValueAsString(MockObjects.getOrder());
        MockHttpServletResponse res = mockMvc.perform(MockMvcRequestBuilders
                .get("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn()
                .getResponse();
        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void test_getOrdersById() throws Exception {
        when(orderService.getOrderByOrderId(Mockito.anyLong())).
                thenReturn(MockObjects.getOrder());
        MockHttpServletResponse res = mockMvc.perform(MockMvcRequestBuilders
                .get("/orders/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}


