package com.code.productservice.service;

import com.code.productservice.DTO.ProductDto;
import com.code.productservice.DTO.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductDto productReq);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void reduceQuantity(long productId, long quantity);
}
