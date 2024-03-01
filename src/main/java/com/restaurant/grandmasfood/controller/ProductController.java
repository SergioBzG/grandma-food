package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping
    public String createProduct() {
        return this.productService.createProduct();
    }

    @GetMapping
    public List<ProductDto> listProducts() {

        return this.productService.findAll();
    }


    @PutMapping(path = "/{uuid}")
    public String updateClient(@PathVariable("uuid") String uuid) {
        return productService.updateProduct();
    }

    @DeleteMapping(path = "/{uuid}")
    public String deleteClient(@PathVariable("uuid") String uuid) {
        return productService.deleteProduct();
    }

    @GetMapping(path = "/{id}")
    public ProductDto getProduct(@PathVariable Long id){
        return productService.getById(id);
    }
}
