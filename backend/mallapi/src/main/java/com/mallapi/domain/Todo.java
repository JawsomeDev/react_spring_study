package com.mallapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @ToString
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 500, nullable = false)
    private String title;

    private String writer;

    private boolean complete;

    private LocalDate dueDate;


    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeComplete(boolean complete) {
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }
}
