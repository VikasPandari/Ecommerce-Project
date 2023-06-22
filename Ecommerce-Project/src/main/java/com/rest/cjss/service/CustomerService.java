package com.rest.cjss.service;
import com.rest.cjss.entity.AddressEntity;
import com.rest.cjss.entity.CustomerEntity;
import com.rest.cjss.entity.CustomerModel;
import com.rest.cjss.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerEntity saveCustomers(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
        return customerEntity;
    }
    public List<CustomerModel> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream().map(customer -> {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setFirstName(customer.getFirstName());
            customerModel.setLastName(customer.getLastName());
            customerModel.setEmail(customer.getEmail());
            customerModel.setMobileNumber(customer.getMobileNumber());
            return customerModel;
                }).collect(Collectors.toList());

    }
    public List<CustomerModel> getCustomer(Integer customerId) {
       Optional<CustomerEntity> customerEntity= customerRepository.findById(customerId);
       return customerEntity.stream().map(customer->{
           CustomerModel customerModel= new CustomerModel();
            customerModel.setFirstName(customer.getFirstName());
            customerModel.setLastName(customer.getLastName());
            customerModel.setEmail(customer.getEmail());
            customerModel.setMobileNumber(customer.getMobileNumber());
            return customerModel;
        }).collect(Collectors.toList());
    }
}
