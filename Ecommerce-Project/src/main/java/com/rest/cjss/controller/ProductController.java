package com.rest.cjss.controller;

import com.rest.cjss.entity.ProductEntity;
import com.rest.cjss.entity.ProductPriceEntity;
import com.rest.cjss.entity.ProductSkusEntity;
import com.rest.cjss.repository.ProductCartRepository;
import com.rest.cjss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private  ProductCartRepository cartRepository;
    @PostMapping("/addProduct")
    public String addProductDetails(@Valid @RequestBody ProductEntity productEntity){
        ProductEntity product= productService.saveProduct(productEntity);
        String message = null;
        if(product!=null){
            message = "product is inserted";
        }
        else {message="product not inserted";}
        return message;
    }
    @PutMapping("/addProductSkus/{productCode}")
    public ProductEntity addProductSkusDetails(@RequestBody ProductSkusEntity productSkusEntity, @PathVariable Integer productCode){
        return  productService.saveProductSkus(productSkusEntity, productCode);

    }
    @PutMapping("/addPriceToProducts/{skuCode}")
    public ProductSkusEntity addPricesToProducts(@RequestBody ProductPriceEntity productPrice, @PathVariable String skuCode){
        return productService.setPriceToSkus(productPrice,skuCode);
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
