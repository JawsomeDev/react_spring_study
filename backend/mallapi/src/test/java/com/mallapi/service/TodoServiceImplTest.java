package com.mallapi.service;

import com.mallapi.domain.Todo;
import com.mallapi.dto.TodoDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class TodoServiceImplTest {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){
        Long tno = 50L;

        log.info(todoService.get(tno));
    }

    @Test
    public void testRegister(){

        TodoDto todoDto = TodoDto.builder()
                .title("Title.....")
                .content("Content.....")
                .dueDate(LocalDate.of(2023,12,31).atStartOfDay())
                .build();

        log.info(todoService.register(todoDto));
    }

}