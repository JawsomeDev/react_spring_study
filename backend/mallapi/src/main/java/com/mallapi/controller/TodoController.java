package com.mallapi.controller;


import com.mallapi.domain.Todo;
import com.mallapi.dto.PageRequestDto;
import com.mallapi.dto.PageResponseDto;
import com.mallapi.dto.TodoDto;
import com.mallapi.service.TodoService;
import com.mallapi.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable("tno") Long tno){

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDto<TodoDto> list(PageRequestDto pageRequestDto){
        log.info("list.............." + pageRequestDto);

        return todoService.getList(pageRequestDto);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDto dto){
        log.info("todoDto: " + dto);

        Long tno = todoService.register(dto);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno, @RequestBody TodoDto todoDto){
        todoDto.setTno(tno);
        todoService.modify(todoDto);

        return Map.of("Result", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable("tno") Long tno){

        todoService.remove(tno);

        return Map.of("Result", "SUCCESS");
    }
}
