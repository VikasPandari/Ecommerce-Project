package com.rest.cjss.entity;

import lombok.Data;

@Data
public class ProductCartModel {
    private int productCode;
    private int customerId;
    private String email;
    private String password;
    private String size;
    private int quantity;
}
