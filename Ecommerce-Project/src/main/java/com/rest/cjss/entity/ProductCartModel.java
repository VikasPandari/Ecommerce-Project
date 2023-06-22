package com.rest.cjss.entity;

import lombok.Data;

@Data
public class ProductCartModel {
    private int productCode;
    private int customerId;
    private String productName;
    private String skuCode;
    private String size;
    private double price;
    private String currency;
    private double totalAmount;
}
