package com.rest.cjss.entity;

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
    private int sno;
    private int productCode;
    private int customerId;
    private String productName;
    private String skuCode;
    private String size;
    private double price;
    private String currency;
    private double totalAmount;
}
