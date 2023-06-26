package com.rest.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Cart_Table")
public class ProductCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int productSno;
    private int productCode;
    private String productName;
    private String skuCode;
    private String size;
    private double price;
    private String currency;
    private int quantity;
    private double totalAmount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customerId")
    private CustomerEntity customerEntity;

}
