package com.mallapi.repository;

import com.mallapi.domain.Todo;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
@Log4j2
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1(){

        Assertions.assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert(){
        Todo todo = Todo.builder()
                .title("title")
                .content("content")
                .dueDate(LocalDate.of(2025,12,30).atStartOfDay())
                .build();

        Todo result = todoRepository.save(todo);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testRead(){

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.info(todo);
    }

    @Test
    public void testUpdate(){
        // 먼저 로딩 하고 엔티티 객체 변경
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.changeTitle("Update Title");
        todo.changeComplete(true);
        todo.changeContent("Update content");

        todoRepository.save(todo);
    }

}