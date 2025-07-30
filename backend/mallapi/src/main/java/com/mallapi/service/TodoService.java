package com.mallapi.service;


import com.mallapi.domain.Todo;
import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.TodoDto;
import jakarta.transaction.Transactional;


@Transactional
public interface TodoService {

    TodoDto get(Long tno);

    Long register(TodoDto dto);

    void modify(TodoDto dto);

    void remove(Long tno);

    PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto);

    default TodoDto entityToDto(Todo todo){


        return TodoDto.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .writer(todo.getWriter())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();
    }

    default Todo dtoToEntity(TodoDto todoDto){

        return Todo.builder()
                .tno(todoDto.getTno())
                .title(todoDto.getTitle())
                .writer(todoDto.getWriter())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate())
                .build();
    }
}
