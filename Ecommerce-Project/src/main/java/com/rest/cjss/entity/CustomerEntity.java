package com.rest.cjss.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customerId")
    private List<AddressEntity> address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "customerDetails")
    private List<ProductOrdersEntity> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEntity")
    private List<ProductCartEntity> cartDetails;
}
