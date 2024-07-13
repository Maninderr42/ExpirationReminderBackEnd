package com.example.jwtAuthentication.Service;

import com.example.jwtAuthentication.Dto.RequsetDto.EditTodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.TodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Exceptions.NotFoundException;
import com.example.jwtAuthentication.Exceptions.UserNotFoundException;

public interface TodoService {


    String addTodo(TodoRequestDto todoRequestDto) throws UserNotFoundException;

    String editTodo(EditTodoRequestDto editTodoRequestDto) throws Exception;

    String deleteTodo(Integer id) throws NotFoundException;
}
