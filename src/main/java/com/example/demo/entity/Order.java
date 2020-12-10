package com.example.demo.entity;

import com.example.demo.dto.OrderItem;
import com.example.demo.util.TWConstants;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name= "ORDER_INFO")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]+", message = TWConstants.VAL_MESSAGE_CUST_NAME)
    @Column(name="CUSTOMER_NAME")
    private String customerName;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = TWConstants.VAL_MESSAGE_DATE_FORMAT)
    @Column(name="ORDER_DATE")
    private String orderDate;
    @Column(name="SHIPPING_ADDRESS")
    private String shippingAddress;
    @Transient
    @Valid
    private List<OrderItem> orderItems;
    @Column(name="TOTAL_COST")
    private double total;
}
