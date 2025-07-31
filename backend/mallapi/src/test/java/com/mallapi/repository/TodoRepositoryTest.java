package com.mallapi.repository;

import com.mallapi.domain.Todo;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
        for (int i = 0; i < 100; i++) {
            Todo todo = Todo.builder()
                    .title("title" + i)
                    .writer("writer" + i)
                    .dueDate(LocalDate.of(2025, 12, 30))
                    .build();
            Todo result = todoRepository.save(todo);
            Assertions.assertNotNull(result);
        }

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
        todo.changeWriter("Update content");

        todoRepository.save(todo);
    }

    @Test
    public void testPaging(){

        //페이지번호는 0부터
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());

        log.info(result.getContent());
    }

//    @Test
//    public void testSearch1(){
//
//        todoRepository.search1();
//    }
}