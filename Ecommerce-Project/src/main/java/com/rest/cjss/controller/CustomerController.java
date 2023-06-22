package com.rest.cjss.controller;

import com.rest.cjss.entity.CustomerEntity;
import com.rest.cjss.entity.CustomerModel;
import com.rest.cjss.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/addCustomers")
    public String addCustomers(@Valid @RequestBody CustomerEntity customer){
    CustomerEntity customers=  customerService.saveCustomers(customer);
    String message=null;
    if(customers!=null){
        message=" customer registered successfully";
    }
    else {message="not registered";}
    return message;
    }
    @GetMapping("/getAllCustomers")
    public List<CustomerModel> getAll(){
      return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomerById/{customerId}")
    public List<CustomerModel> getCustomer(@PathVariable Integer customerId){
       return customerService.getCustomer(customerId);
    }
}
