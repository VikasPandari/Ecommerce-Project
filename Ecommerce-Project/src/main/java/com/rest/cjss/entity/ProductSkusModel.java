package com.rest.cjss.entity;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class ProductSkusModel {
    @NotNull
    private String skuCode;
    @NotBlank
    private String size;
    private List<ProductPriceEntity> productPrices;
}
