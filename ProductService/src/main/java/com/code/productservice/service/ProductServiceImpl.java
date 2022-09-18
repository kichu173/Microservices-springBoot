package com.code.productservice.service;

import com.code.productservice.DTO.ProductDto;
import com.code.productservice.DTO.ProductResponse;
import com.code.productservice.Entity.Product;
import com.code.productservice.exception.ProductServiceCustomException;
import com.code.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long addProduct(ProductDto productReq) {
        log.info("Adding Product..");
        // DTO to Entity
        Product product = Product.builder()
                .productName(productReq.getProductName())
                .price(productReq.getPrice())
                .quantity(productReq.getQuantity())
                .build();
        // storing entity Product
        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("fetching all products..");
        List<Product> products = productRepository.findAll();
        // Mapping Product Entity to ProductDTO obj
        List<ProductResponse> productResponses = products.stream().map(
                product -> {
                    ProductResponse productResponse = new ProductResponse();
                    copyProperties(product, productResponse);// always cross-check product entity and ProductResponse class has same field names. Then only BeanUtils.copyProperties will work.
                    return productResponse;
                }
        ).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Get the product for productId: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();

        copyProperties(product, productResponse);// BeanUtils.copyProperties - imported static to look clean here

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException(
                "Product with given id is not found",
                "PRODUCT_NOT_FOUND"
        ));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity updated Successfully");
    }
}
