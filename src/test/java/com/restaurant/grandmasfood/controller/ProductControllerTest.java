package com.restaurant.grandmasfood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.grandmasfood.exception.InvalidOrMissingDataException;
import com.restaurant.grandmasfood.exception.InvalidSearchingAttributeFormatException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.service.impl.ProductServiceImpl;
import com.restaurant.grandmasfood.utils.ProductTestData;
import com.restaurant.grandmasfood.validator.impl.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;
    @Mock
    private ProductValidator productValidator;
    @Mock
    private BindingResult errors;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testThatCreateProductThrowsInvalidOrMissingDataException() {
        // Data for test
        doThrow(new InvalidOrMissingDataException(
                ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                "Product",
                "AnyField"
                )
        ).when(productValidator)
                .checkMissingData(any(BindingResult.class));
        // Throw exception
        Exception exception = assertThrows(
                InvalidOrMissingDataException.class,
                () -> productController.createProduct(
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        // Asserts
        assertEquals(
                "Invalid or missing AnyField in Product",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkMissingData(any(BindingResult.class));
        verify(productValidator, times(0)).checkCategory(anyString());
        verifyNoInteractions(productService);
    }

    @Test
    void testThatCreateProductThrowsInvalidOrMissingDataExceptionByCategory() {
        // Data for test
        doNothing().when(productValidator)
                .checkMissingData(any(BindingResult.class));
        doThrow(new InvalidOrMissingDataException(
                        ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                        "Product",
                        "category"
                )
        ).when(productValidator)
                .checkCategory(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidOrMissingDataException.class,
                () -> productController.createProduct(
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        assertEquals(
                "Invalid or missing category in Product",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkCategory(anyString());
        verify(productValidator, times(1)).checkMissingData(any(BindingResult.class));
        verifyNoInteractions(productService);
    }

    @Test
    void testThatCreateProductSuccessfullyReturnsProductDto() {
        // Data for test
        ProductDto productDtoToCreate = ProductTestData.createProductDtoA();
        doNothing().when(productValidator)
                .checkMissingData(any(BindingResult.class));
        doNothing().when(productValidator)
                .checkCategory(anyString());
        when(productService.createProduct(any(ProductDto.class)))
                .thenReturn(productDtoToCreate);
        // Create product
        ResponseEntity<ProductDto> response = productController.createProduct(productDtoToCreate, errors);
        // Asserts
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDtoToCreate, response.getBody());
    }

    @Test
    void testThatGetProductByUuidSuccessfullyReturnsProductDto() throws Exception {
        // Data for test
        ProductDto productDtoToReturn = ProductTestData.createProductDtoA();
        doNothing().when(productValidator).checkFormat(anyString());
        when(productService.getProductByUuid(any(UUID.class)))
                .thenReturn(productDtoToReturn);
        // Get product using MockMvc and asserts
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/{uuid}", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string(asJsonString(productDtoToReturn))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.fantasyName").value(productDtoToReturn.getFantasyName())
        );
    }

    @Test
    void testThatGetProductByUuidThrowsInvalidSearchingAttributeFormatException() {
        // Data for test
        doThrow(new InvalidSearchingAttributeFormatException(
                ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                "Product",
                "UUID",
                "invalidUuid"
                )
        ).when(productValidator).checkFormat(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidSearchingAttributeFormatException.class,
                () -> productController.getProduct("invalidUuid")
        );
        // Asserts
        assertEquals(
                "Product invalidUuid UUID no valid",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkFormat(anyString());
    }

    @Test
    void testThatUpdateProductThrowsInvalidOrMissingDataException() {
        // Data for test
        doThrow(new InvalidOrMissingDataException(
                ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                "Product",
                "AnyField"
                )
        ).when(productValidator)
                .checkMissingData(any(BindingResult.class));
        // Throw exception
        Exception exception = assertThrows(
                InvalidOrMissingDataException.class,
                () -> productController.updateProduct(
                        UUID.randomUUID().toString(),
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        // Asserts
        assertEquals(
                "Invalid or missing AnyField in Product",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkMissingData(any(BindingResult.class));
        verify(productValidator, times(0)).checkFormat(anyString());
        verifyNoInteractions(productService);
    }

    @Test
    void testThatUpdateProductThrowsInvalidSearchingAttributeFormatExceptionByUuid() {
        // Data for test
        doNothing().when(productValidator)
                .checkMissingData(any(BindingResult.class));
        doThrow(new InvalidSearchingAttributeFormatException(
                ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                "Product",
                "UUID",
                "invalidUuid"
                )
        ).when(productValidator)
                .checkFormat(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidSearchingAttributeFormatException.class,
                () -> productController.updateProduct(
                        "invalidUuid",
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        // Asserts
        assertEquals(
                "Product invalidUuid UUID no valid",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkFormat(anyString());
        verify(productValidator, times(0)).checkNoUpdatedUuid(anyString(), anyString());
        verifyNoInteractions(productService);
    }


    @Test
    void testThatUpdateProductThrowsInvalidSearchingAttributeFormatExceptionByDifferentUuid() {
        // Data for test
        doNothing().when(productValidator)
                .checkMissingData(any(BindingResult.class));
        doNothing().when(productValidator)
                .checkFormat(anyString());
        doThrow(new InvalidSearchingAttributeFormatException(
                ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                "Product",
                "UUID",
                "invalidUuid"
                )
        ).when(productValidator)
                .checkNoUpdatedUuid(anyString(), anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidSearchingAttributeFormatException.class,
                () -> productController.updateProduct(
                        UUID.randomUUID().toString(),
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        // Asserts
        assertEquals(
                "Product invalidUuid UUID no valid",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkNoUpdatedUuid(anyString(), anyString());
        verify(productValidator, times(0)).checkCategory(anyString());
        verifyNoInteractions(productService);
    }

    @Test
    void testThatUpdateProductThrowsInvalidOrMissingDataExceptionByCategory() {
        // Data for test
        doNothing().when(productValidator)
                .checkMissingData(any(BindingResult.class));
        doNothing().when(productValidator)
                .checkFormat(anyString());
        doNothing().when(productValidator)
                .checkNoUpdatedUuid(anyString(), anyString());
        doThrow(new InvalidOrMissingDataException(
                ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                "Product",
                "category"
                )
        ).when(productValidator)
                .checkCategory(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidOrMissingDataException.class,
                () -> productController.updateProduct(
                        UUID.randomUUID().toString(),
                        ProductTestData.createProductDtoA(),
                        errors
                )
        );
        assertEquals(
                "Invalid or missing category in Product",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkCategory(anyString());
        verifyNoInteractions(productService);
    }
    @Test
    void testThatDeleteProductThrowsInvalidSearchingAttributeFormatException() {
        // Data for test
        doThrow(new InvalidSearchingAttributeFormatException(
                ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                "Product",
                "UUID",
                "invalidUuid"
                )
        ).when(productValidator)
                .checkFormat(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidSearchingAttributeFormatException.class,
                () -> productController.deleteProduct("invalidUuid")
        );
        // Asserts
        assertEquals(
                "Product invalidUuid UUID no valid",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkFormat(anyString());
        verifyNoInteractions(productService);
    }

    @Test
    void testThatDeleteProductSuccessfullyReturnsNoContent() {
        // Data for test
        doNothing().when(productValidator).checkFormat(anyString());
        doNothing().when(productService).deleteProduct(any(UUID.class));
        // Delete product
        ResponseEntity<?> response = productController.deleteProduct(UUID.randomUUID().toString());
        // Asserts
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testThatListProductsReturnsAPageOfProductDto() {
        // Data for test
        Page<ProductDto> productDtoPage = new PageImpl<>(
                List.of(
                        ProductTestData.createProductDtoA(),
                        ProductTestData.createProductDtoB()
                ),
                Pageable.unpaged(),
                2
        );
        when(productService.findAll(any(Pageable.class)))
                .thenReturn(productDtoPage);
        // List products
        ResponseEntity<?> response = productController.listProducts(Pageable.unpaged());
        // Asserts
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDtoPage, response.getBody());
        assertEquals(2, productDtoPage.getTotalElements());
    }

    @Test
    void testThatFilterProductByFantasyNameThrowsInvalidSearchingAttributeFormatException() {
        // Data for test
        doThrow(new InvalidSearchingAttributeFormatException(
                ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                "Product",
                "query param for filtering",
                ""
                )
        ).when(productValidator)
                .checkQueryParamForProductFiltering(anyString());
        // Throw exception
        Exception exception = assertThrows(
                InvalidSearchingAttributeFormatException.class,
                () -> productController.filterProductByFantasyName(" ")
        );
        // Asserts
        assertEquals(
                "Product  query param for filtering no valid",
                exception.getMessage()
        );
        verify(productValidator, times(1)).checkQueryParamForProductFiltering(anyString());
        verifyNoInteractions(productService);
    }

    @Test
    void testThatFilterProductByFantasyNameReturnsAFilteredListOfProduct() {
        // Data for test
        List<ProductDto> productsDtoFiltered = List.of(
                ProductTestData.createProductDtoA(),
                ProductTestData.createProductDtoB()
        );
        doNothing().when(productValidator).checkQueryParamForProductFiltering(anyString());
        when(productService.filterAllByFantasyName(anyString())).thenReturn(productsDtoFiltered);
        // Filter product by fantasy name
        ResponseEntity<List<ProductDto>> response = productController.filterProductByFantasyName(anyString());
        // Asserts
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsDtoFiltered, response.getBody());
    }

    private static String asJsonString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}