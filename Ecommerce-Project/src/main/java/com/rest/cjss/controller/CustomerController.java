package com.rest.cjss.controller;

import com.rest.cjss.entity.*;
import com.rest.cjss.service.CustomerService;
import com.rest.cjss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    ProductService productService;
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

    //    @PutMapping("/{productCode}/getProduct/{size}/{customerId}/{quantity}")
//    public CustomerEntity addProductToCart(@PathVariable Integer productCode,
//                                     @PathVariable String size, @PathVariable Integer customerId,
//                                     @PathVariable Integer quantity){
//        CustomerEntity productCarts= customerService.saveProductToCart(productCode, size,customerId, quantity);
//        return productCarts;
//    }
    @GetMapping("/getAllCustomersWithOrders")
    public List<CustomerEntity> getAll(){
        return customerService.getAllCustomerWithOrders();
    }
    @GetMapping("/getAllCustomers")
    public List<CustomerModel> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @PutMapping("/addCart")
    public CustomerEntity addProductToCart(@RequestBody ProductCartModel productCartModel){
        CustomerEntity cartProduct= customerService.saveProductToCart(productCartModel);
        return cartProduct;
    }
    @PostMapping("/placeOrder")
    public CustomerEntity getOneProductToPlaceOrder(@RequestBody ProductOrderModel productModel){
        CustomerEntity customerProduct=customerService.placeOrder(productModel);
        return customerProduct;
    }

//    @PutMapping("/{productCode}/getOneProduct/{customerId}/{size}/{locationId}/{quantity}")
//    public CustomerEntity getOneProductToPlaceOrder(@PathVariable Integer productCode,
//                                                         @PathVariable Integer customerId,
//                                                         @PathVariable String size, @PathVariable Integer locationId,
//                                                         @PathVariable Integer quantity){
//        CustomerEntity products= customerService.placeOrder(productCode, customerId, size, locationId, quantity);
//        return products;
//    }

    @GetMapping("/getCustomerById/{customerId}")
    public List<CustomerModel> getCustomer(@PathVariable Integer customerId){
        return customerService.getCustomer(customerId);
    }
    @GetMapping("/getCustomerByIdWithOrders/{customerId}")
    public Optional<CustomerEntity> getCustomerWithOrders(@PathVariable Integer customerId){
        return customerService.getCustomerWithOrders(customerId);
    }
}
