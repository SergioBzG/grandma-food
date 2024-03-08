package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.NotFoundException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final Mapper<ProductEntity, ProductDto> productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        boolean exists = this.existsByFantasyName(productDto.getFantasyName());
        if(exists)
            throw new AlreadyExistsException(ExceptionCode.PRODUCT_ALREADY_EXISTS_CODE, "Product", "fantasy name", productDto.getFantasyName());
        productDto.setUuid(UUID.randomUUID());
        productDto.setFantasyName(productDto.getFantasyName().toUpperCase());
        ProductEntity productEntitySaved = this.productRepository.save(productMapper.mapFromDto(productDto));
        return productMapper.mapToDto(productEntitySaved);
    }

    @Override
    public ProductDto getProductByUuid(UUID uuid) {
        Optional<ProductEntity> productOptional = this.productRepository.findByUuid(uuid);
        return productOptional
                .map(this.productMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.PRODUCT_NOT_FOUND_CODE,
                        "Product",
                        "UUID"
                        )
                );
    }

    @Override
    public String updateProduct() {
        return "Product updated";
    }

    @Transactional
    @Override
    public void deleteProduct(UUID uuid) {
        Optional<ProductEntity> productOptional = this.productRepository.findByUuid(uuid);
        productOptional.ifPresentOrElse(
                productEntity -> productEntity.setAvailable(false),
                () -> {throw new NotFoundException(ExceptionCode.PRODUCT_NOT_FOUND_CODE, "Product", "UUID");}
        );
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
