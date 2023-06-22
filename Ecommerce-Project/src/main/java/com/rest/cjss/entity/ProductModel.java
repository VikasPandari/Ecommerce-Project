package com.rest.cjss.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
public class ProductModel {
    @NotEmpty(message = "product code should not be empty or null")
    private int productCode;
    @NotBlank(message = "product name should not be empty or null or blank")
    private String productName;
    @NotBlank
    private String description;
    private List<ProductSkusEntity> productSkus;
}
