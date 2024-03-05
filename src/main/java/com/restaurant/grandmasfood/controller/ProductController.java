package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.exceptions.ExceptionResponse;
import com.restaurant.grandmasfood.exceptions.ProductDoesNotExist;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        // TODO : check all fields are in request body
        boolean exists = this.productService.existsByFantasyName(productDto.getFantasyName());
        if(exists)
            // TODO : question in training session
            // Is required a message ?
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(this.productService.createProduct(productDto), HttpStatus.CREATED);

    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity getProduct(@PathVariable String uuid){
        try {
            UUID correctUuid = UUID.fromString(uuid);
            // TODO : check uuid format, response 400 if it is wrong
            return new ResponseEntity<>(productService.getProductByUuid(correctUuid), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse("P1343", LocalDate.now(), "Invalid uuid format", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (ProductDoesNotExist e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(e.getCode(), LocalDate.now(), e.getMessage(), e.getClass().getName()),
                    HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity deleteProduct(@PathVariable String uuid){
        try {
            UUID correctUuid = UUID.fromString(uuid);
            // TODO : check uuid format, response 400 if it is wrong
            productService.deleteProduct(correctUuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse("P1343", LocalDate.now(), "Invalid uuid format", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (ProductDoesNotExist e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(e.getCode(), LocalDate.now(), e.getMessage(), e.getClass().getName()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
