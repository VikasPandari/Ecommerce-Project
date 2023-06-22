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
@Table(name="Product")
public class ProductEntity {
    @Id
    private int productCode;
    private String productName;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_productCode")
    private List<ProductSkusEntity> productSkus;
    public int getProductCode() {
        return productCode;
    }
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<ProductSkusEntity> getProductSkus() {
        return productSkus;
    }
    public void setProductSkus(List<ProductSkusEntity> productSkus) {
        this.productSkus = productSkus;
    }
}
