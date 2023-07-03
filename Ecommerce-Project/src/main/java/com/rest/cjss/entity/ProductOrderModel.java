package com.rest.cjss.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;
@Data
public class ProductOrderModel {
    private int productCode;
    private String size;
    private String email;
    private String password;
    private int locationId;
    private int quantity;
}
