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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_locationId")
    private List<AddressEntity> address;
}
