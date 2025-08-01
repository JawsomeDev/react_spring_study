package com.mallapi.service;

import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.ProductDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();

        PageResponseDto<ProductDto> responseDto = productService.getList(pageRequestDto);

        log.info(responseDto.getDtoList());
    }

    @Test
    public void testRegister(){

        ProductDto productDto = ProductDto.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품")
                .price(1000)
                .build();

        productDto.setUploadedFileNames(
                List.of(UUID.randomUUID()+ "_" + "TEST1.jpg",
                        UUID.randomUUID()+ "_" + "TEST2.jpg"));
        productService.register(productDto);
    }

}