package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.NoChangesInUpdateException;
import com.restaurant.grandmasfood.exception.NotFoundException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Optional<ProductDto> optionalProductDto = this.findByFantasyName(productDto.getFantasyName());
        if(optionalProductDto.isPresent())
            throw new AlreadyExistsException(
                    ExceptionCode.PRODUCT_ALREADY_EXISTS_CODE,
                    "Product",
                    "fantasy name",
                    productDto.getFantasyName().toUpperCase()
            );
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
    public void updateProduct(ProductDto productDto, UUID uuid) {
        Optional<ProductEntity> savedOptionalProduct = this.productRepository.findByUuid(uuid);
        ProductDto savedProductDto = savedOptionalProduct
                .map(this.productMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.PRODUCT_NOT_FOUND_CODE,
                                "Product",
                                "UUID"
                        )
                );
        Optional<ProductDto> optionalProductDto = this.findByFantasyName(productDto.getFantasyName());
        if(optionalProductDto.isPresent() && !optionalProductDto.get().equals(savedProductDto))
            throw new AlreadyExistsException(
                    ExceptionCode.PRODUCT_ALREADY_EXISTS_CODE,
                    "Product",
                    "fantasy name",
                    productDto.getFantasyName().toUpperCase()
            );
        if(productDto.equals(savedProductDto))
            throw new NoChangesInUpdateException(ExceptionCode.PRODUCT_NO_CHANGES_IN_UPDATE_CODE, "Product");

        // Updated product
        ProductEntity newProductEntity =  productMapper.mapFromDto(productDto);
        newProductEntity.setId(savedOptionalProduct.get().getId());
        this.productRepository.save(newProductEntity);
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
    public Page<ProductDto> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable)
                .map(this.productMapper::mapToDto);
    }

    @Override
    public List<ProductDto> filterAllByFantasyName(String query) {
        return this.productRepository.filterAllByFantasyName(
                Sort.by("fantasyName"),
                query
        ).stream()
                .map(this.productMapper::mapToDto)
                .toList();
    }

    @Override
    public Optional<ProductDto> findByFantasyName(String fantasyName) {
        return this.productRepository.findByFantasyName(fantasyName.toUpperCase())
                .map(this.productMapper::mapToDto);
    }
}
