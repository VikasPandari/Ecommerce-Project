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
@Entity
@Table(name="Customer")
public class CustomerEntity {

    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String mobileNumber;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerDetails")
    private List<AddressEntity> address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "customerDetails")
    private List<ProductOrdersEntity> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEntity")
    private List<ProductCartEntity> cartDetails;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressEntity> getAddress() {
        return address;
    }

    public void setAddress(List<AddressEntity> address) {
        this.address = address;
    }

    public List<ProductOrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductOrdersEntity> orders) {
        this.orders = orders;
    }

    public List<ProductCartEntity> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<ProductCartEntity> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
