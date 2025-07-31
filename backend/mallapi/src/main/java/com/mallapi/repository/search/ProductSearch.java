package com.mallapi.repository.search;

import com.mallapi.domain.Product;
import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.ProductDto;

public interface ProductSearch {

    PageResponseDto<ProductDto> searchList(PageRequestDto pageRequestDto);


}
