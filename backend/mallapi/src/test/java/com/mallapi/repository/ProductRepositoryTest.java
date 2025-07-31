package com.mallapi.repository;

import com.mallapi.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert(){
        Product product = Product.builder()
                .pname("test")
                .pdesc("Test Desc")
                .price(1000)
                .build();
        product.addImageString(UUID.randomUUID()+ "_" + "IMAG1.jpeg");
        product.addImageString(UUID.randomUUID()+ "_" + "IMAG2.jpeg");
        productRepository.save(product);
    }


}