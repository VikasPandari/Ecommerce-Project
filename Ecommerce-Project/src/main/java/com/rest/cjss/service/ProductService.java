package com.rest.cjss.service;

import com.rest.cjss.entity.*;
import com.rest.cjss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductCartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productEntity.setProductSkus(new ArrayList<>());
        return productRepository.save(productEntity);
    }
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
    public ProductEntity getOneProduct(Integer productCode){
        ProductEntity productEntity = productRepository.findById(productCode).get();
        return productEntity;
    }

    public ProductEntity saveProductSkus(ProductSkusEntity productSkusEntity, Integer productCode) {
        productSkusEntity.setProductPrices(new ProductPriceEntity());
        ProductEntity product= productRepository.findById(productCode).get();
        productSkusEntity.setProducts(product);
        product.getProductSkus().add(productSkusEntity);
        productRepository.save(product);
       return product;


    }
    public ProductSkusEntity setPriceToSkus(ProductPriceEntity productPrice, String skuCode){
        ProductSkusEntity productSkus=productSkusRepository.findBySkuCode(skuCode).get();
        productPrice.setProductSkusEntity(productSkus);
        productSkus.setProductPrices(productPrice);
        productSkusRepository.save(productSkus);
        return productSkus;
    }
//    public ProductCartEntity addProductToCart(Integer productCode, String size, Integer customerId, Integer quantity) {
//        Optional<ProductEntity> productEntity = productRepository.findById(productCode);
//        ProductCartEntity productCart= new ProductCartEntity();
//        productEntity.stream().forEach(prod->{prod.getProductSkus().stream().forEach(p->{if(p.getSize().equalsIgnoreCase(size)){
//            productCart.setProductCode(prod.getProductCode());
//            productCart.setProductName(prod.getProductName());
//            productCart.setSkuCode(p.getSkuCode());
//            productCart.setSize(p.getSize());
//            productCart.setPrice(p.getProductPrices().get(0).getPrice());
//            productCart.setCurrency(p.getProductPrices().get(0).getCurrency());
//            productCart.setTotalAmount(quantity*productCart.getPrice());
//        } }); });
//        cartRepository.save(productCart);
//           return productCart;
//    }
//    public ProductOrdersEntity placeOrder(Integer productCode, Integer customerId, String size, Integer locationId, Integer quantity) {
//        Optional<ProductEntity> productEntity = productRepository.findById(productCode);
//        ProductOrdersEntity ordersEntity = new ProductOrdersEntity();
//        productEntity.stream().forEach(prod -> {
//            prod.getProductSkus().stream().forEach(p -> {
//                if (p.getSize().equalsIgnoreCase(size)) {
//                    ordersEntity.setProductCode(prod.getProductCode());
//                    ordersEntity.setProductName(prod.getProductName());
//                    ordersEntity.setSkuCode(p.getSkuCode());
//                    ordersEntity.setSize(p.getSize());
//                    ordersEntity.setPrice(p.getProductPrices().get(0).getPrice());
//                    ordersEntity.setCurrency(p.getProductPrices().get(0).getCurrency());
//                    ordersEntity.setTotalAmount(quantity * ordersEntity.getPrice());
//                }
//            });
//        });
//        ordersRepository.save(ordersEntity);
//        ProductEntity productEntity1 = productRepository.findById(productCode).get();
//        productEntity1.getProductSkus().stream().forEach(product -> {
//            if (product.getSize().equalsIgnoreCase(size)) {
//                product.getProductPrices().get(0).setQuantityAvailable(product.getProductPrices().get(0).getQuantityAvailable() - quantity);
//            }
//        });
//     List<ProductCartEntity> productCarts= cartRepository.findAll();
//     List<ProductCartEntity> productCartEntities=productCarts.stream().filter(p->p.getSize().equalsIgnoreCase(size)&&p.getProductCode()==productCode).collect(Collectors.toList());
//        if(productCartEntities!=null){
//            productCartEntities.clear();
//        }
//        return ordersEntity;
//    }

//    public List<ProductEntity> findProductsBasedOnNames(String pname){
//        List<ProductEntity> products= productRepository.findProductByName(pname);
//        return products;
//    }
}
