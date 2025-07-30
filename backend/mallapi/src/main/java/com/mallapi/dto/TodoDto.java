package com.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    private Long tno;

    private String title;

    private String writer;

    private boolean complete;

    private LocalDate dueDate;


}
