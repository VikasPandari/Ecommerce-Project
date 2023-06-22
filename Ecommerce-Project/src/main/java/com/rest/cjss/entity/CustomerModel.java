package com.rest.cjss.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class CustomerModel {
    @NotNull
    @NotBlank(message = "first name should not be empty or blank")
    private String firstName;
    @NotNull
    @NotBlank(message = "last name should not be empty or blank")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;
    @NotEmpty(message = "password should not be empty or null")
    private String password;

}
