package com.rest.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productSkusEntity", fetch = FetchType.LAZY)
    private ProductPriceEntity productPrices;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="productCode")
    private ProductEntity products;

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

    public ProductPriceEntity getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(ProductPriceEntity productPrices) {
        this.productPrices = productPrices;
    }
    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productSkus")
//    private ProductStockDetails productStockDetails;
}
