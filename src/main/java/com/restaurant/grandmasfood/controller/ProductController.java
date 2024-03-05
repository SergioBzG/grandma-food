package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.utils.ExceptionResponse;
import com.restaurant.grandmasfood.exception.ProductDoesNotExistException;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.service.IProductService;
import com.restaurant.grandmasfood.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDto productDto) {
        // TODO : check all fields are in request body
        try {
            return new ResponseEntity<>(this.productService.createProduct(productDto), HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(e.getCode(), LocalDateTime.now(), e.getMessage(), Arrays.toString(e.getStackTrace())),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity getProduct(@PathVariable String uuid){
        try {
            UUID correctUuid = UUID.fromString(uuid);
            // TODO : check uuid format, response 400 if it is wrong
            return new ResponseEntity<>(productService.getProductByUuid(correctUuid), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse("P1343", LocalDateTime.now(), "Invalid uuid format", Arrays.toString(e.getStackTrace())),
                    HttpStatus.BAD_REQUEST);
        } catch (ProductDoesNotExistException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(e.getCode(), LocalDateTime.now(), e.getMessage(), Arrays.toString(e.getStackTrace())),
                    HttpStatus.BAD_REQUEST);
        }
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
                    new ExceptionResponse("P1343", LocalDateTime.now(), "Invalid uuid format", Arrays.toString(e.getStackTrace())),
                    HttpStatus.BAD_REQUEST);
        } catch (ProductDoesNotExistException e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(e.getCode(), LocalDateTime.now(), e.getMessage(), Arrays.toString(e.getStackTrace())),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<ProductDto> listProducts() {
        return this.productService.findAll();
    }
}
