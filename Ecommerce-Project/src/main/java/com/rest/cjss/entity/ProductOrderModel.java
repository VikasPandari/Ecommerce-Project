package com.rest.cjss.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;
@Data
public class ProductOrderModel {
    private int productCode;
    private String productName;
    private String size;
    private double price;
    private String currency;
    private double totalAmount;
    private String skuCode;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;
    private int locationId;
    private String cityName;
    private String country;
}
