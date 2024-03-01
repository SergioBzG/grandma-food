package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.repository.ProductRepositoryImpl;
import com.restaurant.grandmasfood.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    //@Autowired
    IProductRepository productRepository = null;

    Mapper<ProductEntity, ProductDto> productMapper;

    public ProductServiceImpl(IProductRepository productRepository, Mapper<ProductEntity, ProductDto> productMapper) {

        this.productMapper = productMapper;
        this.productRepository = productRepository;

    }

    @Override
    public String createProduct() {
        return "Product created";
    }


    @Override
    public String updateProduct() {
        return "Product updated";
    }

    @Override
    public String deleteProduct() {
        return "Product deleted";
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.getProductEntityList().stream().map(productMapper::mapToDto).toList();
    }

    @Override
    public String getProduct() {
        return "Product";
    }


    public ProductDto getById(Long id) {
        return productMapper.mapToDto(productRepository.findProductById(id));
    }


}
