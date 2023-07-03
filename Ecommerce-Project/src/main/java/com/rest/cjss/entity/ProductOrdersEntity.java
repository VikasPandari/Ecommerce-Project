package com.rest.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
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
    private int quantity;
    private double totalAmount;
    private int locationId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private CustomerEntity customerDetails;
}
