package com.example.demo.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class OrderItem {
    private long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Must not contain special characters")
    private String productCode;
    private String productName;
    @NotNull
    @Min(1)
    private int quantity;
    @NotNull
    private long orderId;
    @NotNull
    @Min(1)
    private double perItemCost;
}
