package com.rest.cjss.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class ProductSkusEntity {
    @Id
    private String skuCode;
    private String size;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "fk_skuCode")
    private List<ProductPriceEntity> productPrices;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ProductPriceEntity> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPriceEntity> productPrices) {
        this.productPrices = productPrices;
    }
    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productSkus")
//    private ProductStockDetails productStockDetails;
}
