package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.NoChangesInUpdateException;
import com.restaurant.grandmasfood.exception.NotFoundException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.mapper.impl.ProductEntityToDtoMapper;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.utils.ProductTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private IProductRepository productRepository;
    @Mock
    private ProductEntityToDtoMapper productEntityToDtoMapper;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<String> fantasyNameCaptor;
    @Captor
    ArgumentCaptor<ProductEntity> productEntityCaptor;

    @Test
    void testThatCreateProductThrowsAlreadyExistsException() {
        // Data for test
        ProductDto productDto = ProductTestData.createProductDtoA();
        when(productRepository.findByFantasyName(
                fantasyNameCaptor.capture())
        ).thenThrow(
                new AlreadyExistsException(
                        ExceptionCode.PRODUCT_ALREADY_EXISTS_CODE,
                        "Product",
                        "fantasy name",
                        productDto.getFantasyName().toUpperCase()
                )
        );
        // Throw exception
        Exception exception = assertThrows(
                AlreadyExistsException.class,
                () -> productServiceImpl.createProduct(productDto)
        );
        // Asserts
        assertEquals(
                "Product with fantasy name CHOCOLATE CAKE already exists",
                exception.getMessage()
        );
        assertEquals("CHOCOLATE CAKE", fantasyNameCaptor.getValue());
        verifyNoInteractions(productEntityToDtoMapper);
        verify(productRepository, times(0)).save(any());
    }

    @Test
    void testThatCreateProductReturnsProductDto() {
        // Data for test
        ProductDto productDto = ProductTestData.createProductDtoA();
        ProductEntity productEntity = ProductTestData.createProductEntityA();
        when(productRepository.findByFantasyName(anyString())).thenReturn(Optional.empty());
        when(productEntityToDtoMapper.mapFromDto(any())).thenReturn(productEntity);
        when(productEntityToDtoMapper.mapToDto(any())).thenReturn(productDto);
        // Create Product
        ProductDto productSaved = productServiceImpl.createProduct(productDto);
        // Asserts
        assertNotNull(productSaved);
        assertEquals(productDto, productSaved);
        verify(productEntityToDtoMapper, times(1)).mapToDto(any());
        verify(productEntityToDtoMapper, times(1)).mapFromDto(any());
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void testThatGetProductByUuidThrowsNotFoundException() {
        // Data for test
        UUID searchedUuid = UUID.randomUUID();
        when(productRepository.findByUuid(uuidArgumentCaptor.capture())).thenReturn(Optional.empty());
        // Throw exception
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productServiceImpl.getProductByUuid(searchedUuid)
        );
        // Asserts
        assertEquals(
                "Not found Product with this UUID",
                exception.getMessage()
        );
        assertEquals(searchedUuid, uuidArgumentCaptor.getValue());
        verifyNoInteractions(productEntityToDtoMapper);
    }

    @Test
    void testThatGetProductByUuidReturnProductDto() {
        // Data for test
        ProductDto expectedProductDto = ProductTestData.createProductDtoA();
        ProductEntity expectedProductEntity = ProductTestData.createProductEntityA();
        when(productRepository.findByUuid(any())).thenReturn(Optional.of(expectedProductEntity));
        when(productEntityToDtoMapper.mapToDto(any())).thenReturn(expectedProductDto);
        // Get Product
        ProductDto resultProductDto = productServiceImpl.getProductByUuid(expectedProductDto.getUuid());
        // Asserts
        assertNotNull(resultProductDto);
        assertEquals(expectedProductDto, resultProductDto);
        verify(productRepository, times(1)).findByUuid(any());
    }


    @Test
    void testThatUpdateProductThrowsNotFoundException() {
        // Data for test
        ProductDto productDto = ProductTestData.createProductDtoA();
        when(productRepository.findByUuid(uuidArgumentCaptor.capture())).thenReturn(Optional.empty());
        // Throw exception
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productServiceImpl.updateProduct(productDto, productDto.getUuid())
        );
        // Asserts
        assertEquals(
                "Not found Product with this UUID",
                exception.getMessage()
        );
        assertEquals(productDto.getUuid(), uuidArgumentCaptor.getValue());
        verifyNoInteractions(productEntityToDtoMapper);
    }

    @Test
    void testThatUpdateProductThrowsAlreadyExistsException() {
        // Data for test
        ProductDto productDtoToUpdate = ProductTestData.createProductDtoA();
        ProductEntity productEntityToUpdate = ProductTestData.createProductEntityA();
        when(productRepository.findByUuid(any())).thenReturn(Optional.of(productEntityToUpdate));
        when(productEntityToDtoMapper.mapToDto(any())).thenReturn(productDtoToUpdate);
        when(productRepository.findByFantasyName(
                fantasyNameCaptor.capture())
        ).thenThrow(
                new AlreadyExistsException(
                        ExceptionCode.PRODUCT_ALREADY_EXISTS_CODE,
                        "Product",
                        "fantasy name",
                        productDtoToUpdate.getFantasyName().toUpperCase()
                )
        );
        // Throw exception
        Exception exception = assertThrows(
                AlreadyExistsException.class,
                () -> productServiceImpl.updateProduct(productDtoToUpdate, productDtoToUpdate.getUuid())
        );
        // Asserts
        assertEquals(
                "Product with fantasy name " + productDtoToUpdate.getFantasyName() + " already exists",
                exception.getMessage()
        );
        assertEquals(productDtoToUpdate.getFantasyName(), fantasyNameCaptor.getValue());
        verify(productEntityToDtoMapper, times(1)).mapToDto(any());
        verify(productRepository, times(0)).save(any());
    }

    @Test
    void testThatUpdateProductThrowsNoChangesInUpdateException() {
        // Data for test
        ProductDto productDtoToUpdate = ProductTestData.createProductDtoA();
        ProductEntity productEntityToUpdate = ProductTestData.createProductEntityA();
        when(productRepository.findByUuid(any())).thenReturn(Optional.of(productEntityToUpdate));
        when(productEntityToDtoMapper.mapToDto(any())).thenReturn(productDtoToUpdate);
        when(productRepository.findByFantasyName(fantasyNameCaptor.capture())).thenReturn(Optional.of(productEntityToUpdate));
        // Throw exception
        Exception exception = assertThrows(
                NoChangesInUpdateException.class,
                () -> productServiceImpl.updateProduct(productDtoToUpdate, productDtoToUpdate.getUuid())
        );
        // Asserts
        assertEquals(
                "There is no changes in Product",
                exception.getMessage()
        );
        assertEquals(productDtoToUpdate.getFantasyName(),fantasyNameCaptor.getValue());
        verify(productRepository, times(0)).save(any());
    }

    @Test
    void testThatUpdateProductThrowsIsSuccessful() {
        // Data for test
        ProductDto productDtoToUpdate = ProductTestData.createProductDtoA();
        ProductEntity productEntityToUpdate = ProductTestData.createProductEntityA();
        productEntityToUpdate.setUuid(productDtoToUpdate.getUuid());

        ProductDto newProductDto = ProductTestData.createProductDtoAUpdated();
        newProductDto.setUuid(productDtoToUpdate.getUuid());

        ProductEntity newProductEntity = ProductTestData.createProductEntityAUpdated();
        newProductEntity.setUuid(productDtoToUpdate.getUuid());

        when(productRepository.findByUuid(uuidArgumentCaptor.capture())).thenReturn(Optional.of(productEntityToUpdate));
        when(productEntityToDtoMapper.mapToDto(any())).thenReturn(productDtoToUpdate);
        when(productRepository.findByFantasyName(fantasyNameCaptor.capture())).thenReturn(Optional.of(productEntityToUpdate));
        when(productEntityToDtoMapper.mapFromDto(any())).thenReturn(newProductEntity);
        // Update product
        productServiceImpl.updateProduct(newProductDto, newProductDto.getUuid());
        // Asserts
        assertEquals(productDtoToUpdate.getUuid(), uuidArgumentCaptor.getValue());
        assertEquals(productDtoToUpdate.getFantasyName(), fantasyNameCaptor.getValue());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void testThatDeleteProductThrowsNotFoundException() {
        // Data for test
        UUID sentUuid = UUID.randomUUID();
        when(productRepository.findByUuid(uuidArgumentCaptor.capture()))
                .thenReturn(Optional.empty());
        // Throw exception
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productServiceImpl.deleteProduct(sentUuid)
        );
        // Asserts
        assertEquals(
                "Not found Product with this UUID",
                exception.getMessage()
        );
        assertEquals(sentUuid, uuidArgumentCaptor.getValue());
    }

    @Test
    void testThatDeleteProductIsSuccessful() {
        // Data for test
        ProductEntity productToDelete = ProductTestData.createProductEntityB();
        when(productRepository.findByUuid(uuidArgumentCaptor.capture()))
                .thenReturn(Optional.of(productToDelete));
        // Throw exception
        productServiceImpl.deleteProduct(productToDelete.getUuid());
        // Asserts
        assertFalse(productToDelete.getAvailable());
        verify(productRepository, times(1)).findByUuid(any());
        assertEquals(productToDelete.getUuid(), uuidArgumentCaptor.getValue());
    }

    @Test
    void testThatFindAllReturnsAPage() {
        // Data for test
        when(productRepository.findAll(Pageable.ofSize(3))).thenReturn(Page.empty());
        // List products
        Page<ProductDto> page = productServiceImpl.findAll(Pageable.ofSize(3));
        // asserts
        assertThat(page).hasSize(0);
        verify(productEntityToDtoMapper, times(0)).mapToDto(any());
    }

    @Test
    void filterAllByFantasyName() {
        // Data for test
        List<ProductEntity> filteredProducts = List.of(
                ProductTestData.createProductEntityA(),
                ProductTestData.createProductEntityB()
        );
        when(productRepository.filterAllByFantasyName(any(Sort.class), anyString()))
                .thenReturn(filteredProducts);
        when(productEntityToDtoMapper.mapToDto(productEntityCaptor.capture()))
                .thenReturn(ProductTestData.createProductDtoA())
                .thenReturn(ProductTestData.createProductDtoB());
        // List filtered products
        List<ProductDto> products = productServiceImpl.filterAllByFantasyName("query");
        // Asserts
        assertEquals(filteredProducts.size(), products.size());
        verify(productEntityToDtoMapper, times(2)).mapToDto(any());
        assertEquals(filteredProducts.getFirst(), productEntityCaptor.getAllValues().getFirst());
        assertEquals(filteredProducts.get(1), productEntityCaptor.getAllValues().get(1));
    }
}