package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.Product;
import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.ProductDoesNotExistException;
import com.restaurant.grandmasfood.exception.utils.Code;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    IProductRepository productRepository;

    Mapper<Product, ProductDto> productMapper;

    public ProductServiceImpl(IProductRepository productRepository, Mapper<Product, ProductDto> productMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;

    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws AlreadyExistsException {
        boolean exists = this.existsByFantasyName(productDto.getFantasyName());
        if(exists)
            throw new AlreadyExistsException(Code.PRODUCT_ALREADY_EXISTS_CODE, "Product", "fantasy name", productDto.getFantasyName());

        productDto.setUuid(UUID.randomUUID());
        productDto.setFantasyName(productDto.getFantasyName().toUpperCase());
        Product productSaved = this.productRepository.save(productMapper.mapFromDto(productDto));
        return productMapper.mapToDto(productSaved);
    }

    @Override
    public ProductDto getProductByUuid(UUID uuid) throws ProductDoesNotExistException {
        Optional<Product> productOptional = this.productRepository.findByUuid(uuid);
        return productOptional.map(product -> this.productMapper.mapToDto(product)
        ).orElseThrow( () -> new ProductDoesNotExistException(uuid));
    }


    @Override
    public String updateProduct() {
        return "Product updated";
    }

    @Transactional
    @Override
    public void deleteProduct(UUID uuid) throws ProductDoesNotExistException {
        Optional<Product> productOptional = this.productRepository.findByUuid(uuid);
        if(productOptional.isEmpty())
            throw new ProductDoesNotExistException(uuid);
        Product product =  productOptional.get();
        product.setAvailable(false);
    }

    @Override
    public List<ProductDto> findAll() {
        return this.productRepository.findAll().stream()
                .map(this.productMapper::mapToDto)
                .toList();
    }



    @Override
    public boolean existsByFantasyName(String fantasyName) {
        return this.productRepository.existsByFantasyName(fantasyName.toUpperCase());
    }
}
