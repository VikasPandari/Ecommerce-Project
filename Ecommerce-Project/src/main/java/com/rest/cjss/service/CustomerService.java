package com.rest.cjss.service;
import com.rest.cjss.entity.*;
import com.rest.cjss.exception.NoCustomerFoundException;
import com.rest.cjss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private ProductCartRepository productCartRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
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
        Optional<CustomerEntity> customerEntity1 = customerRepository.findById(productCartModel.getCustomerId());
        if (customerEntity1.get().getEmail().equalsIgnoreCase(productCartModel.getEmail()) &&
                customerEntity1.get().getPassword().equalsIgnoreCase(productCartModel.getPassword())) {
            ProductCartEntity productCart = new ProductCartEntity();
            productEntity.stream().forEach(prod -> {
                prod.getProductSkus().stream().forEach(p -> {
                    int qty = p.getProductPrices().get(0).getQuantityAvailable();
                    if (p.getSize().equalsIgnoreCase(productCartModel.getSize()) && qty >= productCartModel.getQuantity()) {
                        if(customerEntity1.get().getCartDetails().isEmpty()||customerEntity1.get().getCartDetails().get(0).getProductCode()==productCartModel.getProductCode()&&
                                (!customerEntity1.get().getCartDetails().get(0).getSize().equalsIgnoreCase(productCartModel.getSize()))){
                            productCart.setProductCode(prod.getProductCode());
                            productCart.setProductName(prod.getProductName());
                            productCart.setSkuCode(p.getSkuCode());
                            productCart.setSize(p.getSize());
                            productCart.setPrice(p.getProductPrices().get(0).getPrice());
                            productCart.setCurrency(p.getProductPrices().get(0).getCurrency());
                            productCart.setQuantity(productCartModel.getQuantity());
                            productCart.setTotalAmount(productCartModel.getQuantity() * productCart.getPrice());
                            productCart.setCustomerEntity(customerEntity1.get());
                            customerEntity1.get().getCartDetails().add(productCart);
                        }
                        else if ( customerEntity1.get().getCartDetails().get(0).getProductCode()==productCartModel.getProductCode()&&
                                customerEntity1.get().getCartDetails().get(0).getSize().equalsIgnoreCase(productCartModel.getSize())){
                            customerEntity1.get().getCartDetails().get(0).setQuantity(productCartModel.getQuantity()+customerEntity1.get().getCartDetails().get(0).getQuantity());
                        }
                    }
                });
            });

            customerRepository.save(customerEntity1.get());
            return customerEntity1.get();
        }else {
            throw  new NoCustomerFoundException("your are entered invalid username and password or please register to add product to cart");
        }
    }
    public CustomerEntity placeOrder(ProductOrderModel productOrderModel) throws NoCustomerFoundException{
        Optional<ProductEntity> productEntity = productRepository.findById(productOrderModel.getProductCode());
        CustomerEntity customerDetails = customerRepository.findById(productOrderModel.getCustomerId()).get();
        if (customerDetails.getEmail().equalsIgnoreCase(productOrderModel.getEmail()) &&
                customerDetails.getPassword().equalsIgnoreCase(productOrderModel.getPassword())) {
            ProductOrdersEntity ordersEntity = new ProductOrdersEntity();
            if(customerDetails.getCartDetails().isEmpty()||
                    (!customerDetails.getCartDetails().get(0).getSize().equalsIgnoreCase(productOrderModel.getSize()))&&
                            customerDetails.getCartDetails().get(0).getProductCode()==productOrderModel.getProductCode()||
                    customerDetails.getCartDetails().get(0).getProductCode()!=productOrderModel.getProductCode()) {
                productEntity.stream().forEach(prod -> {
                    prod.getProductSkus().stream().forEach(p -> {
                        if (p.getSize().equalsIgnoreCase(productOrderModel.getSize()) &&
                                p.getProductPrices().get(0).getQuantityAvailable()>=productOrderModel.getQuantity()) {
                            ordersEntity.setProductCode(prod.getProductCode());
                            ordersEntity.setProductName(prod.getProductName());
                            ordersEntity.setSkuCode(p.getSkuCode());
                            ordersEntity.setSize(p.getSize());
                            ordersEntity.setPrice(p.getProductPrices().get(0).getPrice());
                            ordersEntity.setCurrency(p.getProductPrices().get(0).getCurrency());
                            ordersEntity.setQuantity(productOrderModel.getQuantity());
                            ordersEntity.setTotalAmount(productOrderModel.getQuantity() * ordersEntity.getPrice());
                            ordersEntity.setLocationId(productOrderModel.getLocationId());
                        }});});
            }
            else{
                ProductCartEntity cartEntity= customerDetails.getCartDetails().get(0);
                ordersEntity.setLocationId(productOrderModel.getLocationId());
                ordersEntity.setProductCode(cartEntity.getProductCode());
                ordersEntity.setSize(cartEntity.getSize());
                ordersEntity.setPrice(cartEntity.getPrice());
                ordersEntity.setProductName(cartEntity.getProductName());
                ordersEntity.setSkuCode(cartEntity.getSkuCode());
                ordersEntity.setCurrency(cartEntity.getCurrency());
                ordersEntity.setQuantity(productOrderModel.getQuantity());
                ordersEntity.setTotalAmount(productOrderModel.getQuantity()*ordersEntity.getPrice());
                if(cartEntity.getQuantity()>1) {
                    cartEntity.setQuantity(cartEntity.getQuantity()-productOrderModel.getQuantity());
                }
                else{List<ProductCartEntity> customer=customerDetails.getCartDetails().stream().collect(Collectors.toList());
                    customer.clear();
                    customerDetails.setCartDetails(customer);
                }
            }
            ProductEntity productEntity1 = productRepository.findById(productOrderModel.getProductCode()).get();
            productEntity1.getProductSkus().stream().forEach(product -> {
                if (product.getSize().equalsIgnoreCase(productOrderModel.getSize())) {
                    product.getProductPrices().get(0).setQuantityAvailable(product.getProductPrices().get(0).getQuantityAvailable()-productOrderModel.getQuantity());
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
