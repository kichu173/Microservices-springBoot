package com.code.productservice.DTO;

import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private Long price;
    private Long quantity;
}
