package com.mallapi.service;

import com.mallapi.domain.Product;
import com.mallapi.domain.ProductImage;
import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.ProductDto;
import com.mallapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PageResponseDto<ProductDto> getList(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductDto> dtoList = result.get().map(arr -> {

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            ProductDto productDto = ProductDto.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();


            String imageStr = productImage.getFileName();
            productDto.setUploadedFileNames(List.of(imageStr));
            return productDto;
        }).toList();

        long totalCount = result.getTotalElements();

        return PageResponseDto.<ProductDto>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDto(pageRequestDto)
                .build();
    }

    @Override
    public Long register(ProductDto productDto) {
        Product product = dtoToEntity(productDto);

        log.info("-----------------");
        log.info(product);
        log.info(product.getImageList());

        return productRepository.save(product).getPno();
    }

    @Override
    public ProductDto get(Long pno) {
        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        return entityToDto(product);
    }

    @Override
    public void modify(ProductDto productDto) {
        // 조회
        Optional<Product> result = productRepository.findById(productDto.getPno());

        // 변경 내용 반영
        Product product = result.orElseThrow();
        product.changePrice(productDto.getPrice());
        product.changeName(productDto.getPname());
        product.changeDesc(productDto.getPdesc());
        product.changeDel(productDto.isDelFlag());

        // 이미지 처리
        List<String> uploadedFileNames = productDto.getUploadedFileNames();

        product.clearList();

        if(uploadedFileNames != null && !uploadedFileNames.isEmpty()) {
            uploadedFileNames.forEach(product::addImageString);
        }

        // 저장
        productRepository.save(product);
    }

    @Override
    public void remove(Long pno) {
        productRepository.deleteById(pno);
    }

    private ProductDto entityToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .delFlag(product.isDelFlag())
                .build();
        
        List<ProductImage> imageList = product.getImageList();
        
        if(imageList == null || imageList.isEmpty()){
            return productDto;
        }

        List<String> fileNameList = imageList.stream().map(ProductImage::getFileName).toList();

        productDto.setUploadedFileNames(fileNameList);

        return productDto;
    }

    private Product dtoToEntity(ProductDto productDto) {
        Product product = Product.builder()
                .pno(productDto.getPno())
                .pname(productDto.getPname())
                .pdesc(productDto.getPdesc())
                .price(productDto.getPrice())
                .delFlag(productDto.isDelFlag())
                .build();

        List<String> uploadedFileNames = productDto.getUploadedFileNames();
        if(uploadedFileNames == null || uploadedFileNames.isEmpty()) {
            return product;
        }
        uploadedFileNames.forEach(fileName -> {
            product.addImageString(fileName);
        });

        return product;
    }
}
