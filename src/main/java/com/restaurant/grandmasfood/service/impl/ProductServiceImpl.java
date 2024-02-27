package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Override
    public String createProduct() {
        return "Product created";
    }

    @Override
    public String getProduct() {
        return "Product";
    }

    @Override
    public String updateProduct() {
        return "Product updated";
    }

    @Override
    public String deleteProduct() {
        return "Product deleted";
    }
}
