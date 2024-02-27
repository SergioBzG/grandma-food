package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping
    public String createProduct() {
        return this.productService.createProduct();
    }

    @GetMapping(path = "/{uuid}")
    public String getProduct(@PathVariable("uuid") String uuid) {
        return this.productService.getProduct();
    }

    @PutMapping(path = "/{uuid}")
    public String updateClient(@PathVariable("uuid") String uuid) {
        return productService.updateProduct();
    }

    @DeleteMapping(path = "/{uuid}")
    public String deleteClient(@PathVariable("uuid") String uuid) {
        return productService.deleteProduct();
    }
}
