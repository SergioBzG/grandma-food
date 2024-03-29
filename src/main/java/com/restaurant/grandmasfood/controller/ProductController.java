package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.service.IProductService;
import com.restaurant.grandmasfood.validator.impl.ProductValidator;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final IProductService productService;
    private final ProductValidator productValidator;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated ProductDto productDto, BindingResult errors) {
        this.productValidator.checkMissingData(errors);
        this.productValidator.checkCategory(productDto.getCategory());
        return new ResponseEntity<>(this.productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String uuid){
        this.productValidator.checkFormat(uuid);
        return new ResponseEntity<>(productService.getProductByUuid(UUID.fromString(uuid)), HttpStatus.OK);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> updateProduct(@PathVariable("uuid") String uuid, @RequestBody @Validated ProductDto productDto, BindingResult errors) {
        this.productValidator.checkMissingData(errors);
        this.productValidator.checkFormat(uuid);
        this.productValidator.checkNoUpdatedUuid(uuid, productDto.getUuid().toString());
        this.productValidator.checkCategory(productDto.getCategory());
        productService.updateProduct(productDto, UUID.fromString(uuid));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteProduct(@PathVariable String uuid) {
        this.productValidator.checkFormat(uuid);
        productService.deleteProduct(UUID.fromString(uuid));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> listProducts(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(this.productService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<ProductDto>>  filterProductByFantasyName(
            @RequestParam(required = false) String q
    ) {
        this.productValidator.checkQueryParamForProductFiltering(q);
        return new ResponseEntity<>(this.productService.filterAllByFantasyName(q), HttpStatus.OK);
    }
}
