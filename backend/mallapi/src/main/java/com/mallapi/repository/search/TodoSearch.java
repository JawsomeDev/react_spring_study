package com.mallapi.repository.search;

import com.mallapi.domain.Todo;
import com.mallapi.dto.PageRequestDto;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDto pageRequestDto);
}
