package com.rest.cjss.repository;

import com.rest.cjss.entity.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, Integer> {


//    public Optional<List<ProductCartEntity>> findProductBySize(String size);
//    SELECT * FROM ((product
//            INNER JOIN product_skus  ON product.product_code = product_skus.fk_product_code)
//    INNER JOIN product_price ON product_skus.sku_code = product_price.fk_sku_code);
//   @Query("select * from ((Product Inner Join ProductSkus ON product.product_code =:productCode = product_skus.fk_product_code=:productCode)INNER JOIN product_price ON product_skus.sku_code=:skuCode = product_price.fk_sku_code=:skuCode))")
//   List<ProductEntity> findProductByCode(@Param("productCode") Integer productCode,
//                                         @Param("skuCode") Integer skuCode);
}
