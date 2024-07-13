package com.example.jwtAuthentication.Trnasformations;

import com.example.jwtAuthentication.Dto.RequsetDto.TodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Models.Todo;

public class TodoTransformation {

    public static Todo convertTodoEntity(TodoRequestDto todoRequestDto) {

        Todo todo = Todo.builder()
                .title(todoRequestDto.getTitle())
                .content(todoRequestDto.getContent())
                .build();

        return todo;
    }
}
