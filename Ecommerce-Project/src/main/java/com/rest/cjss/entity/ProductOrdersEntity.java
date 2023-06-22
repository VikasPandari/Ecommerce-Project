package com.rest.cjss.entity;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
@Data
@Entity
public class ProductOrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sno;
    private int productCode;
    private String skuCode;
    private String productName;
    private String size;
    private double price;
    private String currency;
    private double totalAmount;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private int locationId;
    private String cityName;
    private String country;
}
