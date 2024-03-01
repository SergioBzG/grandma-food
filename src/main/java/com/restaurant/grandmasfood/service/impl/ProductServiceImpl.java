package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.Product;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    //@Autowired
    IProductRepository productRepository;

    Mapper<Product, ProductDto> productMapper;

    public ProductServiceImpl(IProductRepository productRepository, Mapper<Product, ProductDto> productMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;

    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        productDto.setUuid(UUID.randomUUID());
        Product productSaved = this.productRepository.save(productMapper.mapFromDto(productDto));
        return productMapper.mapToDto(productSaved);
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
        return null;
    }

    @Override
    public String getProduct() {
        return "Product";
    }


    public ProductDto getById(Long id) {
        return null;
    }


}
