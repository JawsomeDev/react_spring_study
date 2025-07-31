package com.mallapi.repository;

import com.mallapi.domain.Product;
import com.mallapi.dto.PageRequestDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert(){
        for (int i = 0; i< 10; i++){
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


    @Transactional
    @Test
    public void testRead(){

        Long pno = 1L;

        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        log.info(product);

        log.info(product.getImageList());
    }


    @Test
    public void testRead2(){

        Long pno = 1L;

        Optional<Product> result = productRepository.selectOne(pno);

        Product product = result.orElseThrow();

        log.info(product);

        log.info(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete(){
        Long pno = 2L;

        productRepository.updateToDelete(true, pno);
    }

    @Test
    public void testUpdate(){
        Product product = productRepository.selectOne(1L).get();

        product.changePrice(3000);
        product.clearList();
        product.addImageString(UUID.randomUUID()+ "_" + "PIMAGE1.jpeg");

        product.addImageString(UUID.randomUUID()+ "_" + "PIMAGE2.jpeg");

        product.addImageString(UUID.randomUUID()+ "_" + "PIMAGE3.jpeg");

        productRepository.save(product);
    }

    @Test
    public void testList(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

        result.getContent().forEach(arr ->  log.info(Arrays.toString(arr)));
    }


    @Test
    public void testSearch(){

        PageRequestDto pageRequestDto = PageRequestDto.builder().build();

        productRepository.searchList(pageRequestDto);
    }
}