package com.mallapi.service;

import com.mallapi.domain.Todo;
import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.TodoDto;
import com.mallapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public TodoDto get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        return entityToDto(todo);
    }

    @Override
    public Long register(TodoDto dto) {
        Todo todo = dtoToEntity(dto);

        Todo result = todoRepository.save(todo);

        return result.getTno();
    }

    @Override
    public void modify(TodoDto dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());
        todo.changeWriter(dto.getWriter());
        todo.changeComplete(dto.isComplete());
        todo.changeDueDate(dto.getDueDate());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto) {

        //JPA
        Page<Todo> result = todoRepository.search1(pageRequestDto);

        //Todo List => TodoDto List
        List<TodoDto> dtoList = result.get().map(this::entityToDto).toList();

        PageResponseDto<TodoDto> responseDto =
                PageResponseDto.<TodoDto>withAll()
                        .dtoList(dtoList)
                        .pageRequestDto(pageRequestDto)
                        .total(result.getTotalElements())
                        .build();
        return responseDto;

    }
}
