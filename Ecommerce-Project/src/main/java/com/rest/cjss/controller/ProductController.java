package com.rest.cjss.controller;

import com.rest.cjss.entity.ProductCartEntity;
import com.rest.cjss.entity.ProductEntity;
import com.rest.cjss.entity.ProductModel;
import com.rest.cjss.entity.ProductOrdersEntity;
import com.rest.cjss.repository.ProductCartRepository;
import com.rest.cjss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private  ProductCartRepository cartRepository;
    @PostMapping("/addProductAndSkusDetails")
    public String addProductDetails(@Valid @RequestBody ProductEntity productEntity){
        ProductEntity product= productService.saveProductDetails(productEntity);
        String message = null;
        if(product!=null){
            message = "product is inserted";
        }
        else {message="product not inserted";}
        return message;
    }
    @GetMapping("/getAllProducts")
    public List<ProductEntity> getAll(){
        List<ProductEntity> products= productService.getAllProducts();
        return products;
    }
    @GetMapping("/getProduct/{productCode}")
    public ProductEntity getOneProduct(@PathVariable Integer productCode){
        ProductEntity product= productService.getOneProduct(productCode);
        return product;
    }
//    @GetMapping("/{productCode}/getProduct/{size}/{customerId}/{quantity}")
//    public ProductCartEntity Product(@PathVariable Integer productCode,
//                                     @PathVariable String size, @PathVariable Integer customerId,
//                                     @PathVariable Integer quantity){
//        ProductCartEntity productCarts= productService.addProductToCart(productCode, size,customerId, quantity);
//        return productCarts;
//    }
//
//    @GetMapping("/{productCode}/getOneProduct/{customerId}/{size}/{locationId}/{quantity}")
//    public ProductOrdersEntity getOneProductToPlaceOrder(@PathVariable Integer productCode,
//                                                         @PathVariable Integer customerId,
//                                                         @PathVariable String size, @PathVariable Integer locationId,
//                                                         @PathVariable Integer quantity){
//        ProductOrdersEntity products= productService.placeOrder(productCode, customerId, size, locationId, quantity);
//        return products;
//    }

//    @GetMapping("/getAllProductsByName/{pname}")
//    public List<ProductEntity> getAllProducts(@PathVariable String pname){
//        return  productService.findProductsBasedOnNames(pname);
//    }
}
