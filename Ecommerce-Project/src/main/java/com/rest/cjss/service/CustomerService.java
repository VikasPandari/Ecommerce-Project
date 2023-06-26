package com.rest.cjss.service;
import com.rest.cjss.entity.*;
import com.rest.cjss.exception.NoCustomerFoundException;
import com.rest.cjss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCartRepository cartRepository;
    public CustomerEntity saveCustomers(CustomerEntity customerEntity) {
        customerEntity.setOrders(new ArrayList<>());
        customerEntity.setCartDetails(new ArrayList<>());
        customerRepository.save(customerEntity);
        return customerEntity;
    }
    public List<CustomerEntity> getAllCustomerWithOrders(){
        return customerRepository.findAll();
    }
    public CustomerEntity saveProductToCart(ProductCartModel productCartModel) throws NoCustomerFoundException{
        Optional<ProductEntity> productEntity = productRepository.findById(productCartModel.getProductCode());
        CustomerEntity customerEntity1 = customerRepository.findById(productCartModel.getCustomerId()).get();
        Optional<ProductCartEntity> productCartEntity= cartRepository.findById(productCartModel.getProductCode());
        if (customerEntity1.getEmail().equalsIgnoreCase(productCartModel.getEmail()) &&
                customerEntity1.getPassword().equalsIgnoreCase(productCartModel.getPassword())) {
            ProductCartEntity productCart = new ProductCartEntity();
            productEntity.stream().forEach(prod -> {
                prod.getProductSkus().stream().forEach(p -> {
                    int qty = p.getProductPrices().get(0).getQuantityAvailable();
                    if (p.getSize().equalsIgnoreCase(productCartModel.getSize()) && qty >= productCartModel.getQuantity()) {
                        if (productCartEntity.isPresent()) {
                            ProductCartEntity productCart1= productCartEntity.get();
                            if (productCart1.getSize().equalsIgnoreCase(productCartModel.getSize()) &&
                                    productCart1.getProductCode() == productCartModel.getProductCode() &&
                                    productCart1.getCustomerEntity().getCustomerId() == productCartModel.getCustomerId()) {
                                productCart1.setQuantity(productCart1.getQuantity() + productCartModel.getQuantity());
                                cartRepository.save(productCart1);
                            } else {
                                productCart.setProductCode(prod.getProductCode());
                                productCart.setProductName(prod.getProductName());
                                productCart.setSkuCode(p.getSkuCode());
                                productCart.setSize(p.getSize());
                                productCart.setPrice(p.getProductPrices().get(0).getPrice());
                                productCart.setCurrency(p.getProductPrices().get(0).getCurrency());
                                productCart.setQuantity(productCartModel.getQuantity());
                                productCart.setTotalAmount(productCartModel.getQuantity() * productCart.getPrice());
                                productCart.setCustomerEntity(customerEntity1);
                                customerEntity1.getCartDetails().add(productCart);
                            }
                        }else{
                            productCart.setProductCode(prod.getProductCode());
                            productCart.setProductName(prod.getProductName());
                            productCart.setSkuCode(p.getSkuCode());
                            productCart.setSize(p.getSize());
                            productCart.setPrice(p.getProductPrices().get(0).getPrice());
                            productCart.setCurrency(p.getProductPrices().get(0).getCurrency());
                            productCart.setQuantity(productCartModel.getQuantity());
                            productCart.setTotalAmount(productCartModel.getQuantity() * productCart.getPrice());
                            productCart.setCustomerEntity(customerEntity1);
                            customerEntity1.getCartDetails().add(productCart);
                        }
                    }
                });
            });
            customerRepository.save(customerEntity1);
            return customerEntity1;
        }else {
            throw  new NoCustomerFoundException("your are entered invalid username and password or please register to add product to cart");
        }
    }
    public CustomerEntity placeOrder(ProductOrderModel productModel) throws NoCustomerFoundException{
        Optional<ProductEntity> productEntity = productRepository.findById(productModel.getProductCode());
        CustomerEntity customerDetails = customerRepository.findById(productModel.getCustomerId()).get();
        if (customerDetails.getEmail().equalsIgnoreCase(productModel.getEmail()) &&
                customerDetails.getPassword().equalsIgnoreCase(productModel.getPassword())) {
            ProductOrdersEntity ordersEntity = new ProductOrdersEntity();
            productEntity.stream().forEach(prod -> {
                prod.getProductSkus().stream().forEach(p -> {
                    if (p.getSize().equalsIgnoreCase(productModel.getSize()) &&
                            p.getProductPrices().get(0).getQuantityAvailable()>=productModel.getQuantity()) {
                        ordersEntity.setProductCode(prod.getProductCode());
                        ordersEntity.setProductName(prod.getProductName());
                        ordersEntity.setSkuCode(p.getSkuCode());
                        ordersEntity.setSize(p.getSize());
                        ordersEntity.setPrice(p.getProductPrices().get(0).getPrice());
                        ordersEntity.setCurrency(p.getProductPrices().get(0).getCurrency());
                        ordersEntity.setQuantity(productModel.getQuantity());
                        ordersEntity.setTotalAmount(productModel.getQuantity() * ordersEntity.getPrice());
                        ordersEntity.setLocationId(productModel.getLocationId());
                    }});});
            ProductEntity productEntity1 = productRepository.findById(productModel.getProductCode()).get();
            productEntity1.getProductSkus().stream().forEach(product -> {
                if (product.getSize().equalsIgnoreCase(productModel.getSize())) {
                    product.getProductPrices().get(0).setQuantityAvailable(product.getProductPrices().get(0).getQuantityAvailable()-productModel.getQuantity());
                }
            });
            ordersEntity.setCustomerDetails(customerDetails);
            customerDetails.getOrders().add(ordersEntity);
            ordersRepository.save(ordersEntity);
            return customerDetails;
        }else {
            throw  new NoCustomerFoundException("your are entered invalid username and password or please register to place your order");
        }
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
    public List<CustomerModel> getCustomer(Integer customerId) throws NoCustomerFoundException {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            return customerEntity.stream().map(customer -> {
                CustomerModel customerModel = new CustomerModel();
                customerModel.setFirstName(customer.getFirstName());
                customerModel.setLastName(customer.getLastName());
                customerModel.setEmail(customer.getEmail());
                customerModel.setMobileNumber(customer.getMobileNumber());
                return customerModel;
            }).collect(Collectors.toList());
        }
        else {
            throw new NoCustomerFoundException("Your are entered invalid customer Id");
        }
    }

    public Optional<CustomerEntity> getCustomerWithOrders(Integer customerId) throws NoCustomerFoundException {
        Optional<CustomerEntity>  customerEntities=  customerRepository.findById(customerId);
        if(customerEntities.isPresent()) {
            return customerEntities;
        }
        else{
            throw  new NoCustomerFoundException("Your are entered invalid customer Id");
        }
    }
}
