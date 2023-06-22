package com.rest.cjss.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductPriceModel {
    @Positive
    private double price;
    @NotNull
    private String currency;
    @Positive
    private int quantityAvailable;
}
