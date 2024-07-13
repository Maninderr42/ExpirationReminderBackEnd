package com.example.jwtAuthentication.Service.Impl;

import com.example.jwtAuthentication.Dto.RequsetDto.EditTodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.TodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Exceptions.NotFoundException;
import com.example.jwtAuthentication.Exceptions.UserNotFoundException;
import com.example.jwtAuthentication.Models.Todo;
import com.example.jwtAuthentication.Models.User;
import com.example.jwtAuthentication.Repository.TodoRespository;
import com.example.jwtAuthentication.Repository.UserRepository;
import com.example.jwtAuthentication.Service.TodoService;
import com.example.jwtAuthentication.Trnasformations.TodoTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRespository todoRespository;

    @Override
    public String addTodo(TodoRequestDto todoRequestDto) throws UserNotFoundException {


        Todo todo= TodoTransformation.convertTodoEntity(todoRequestDto);

        Optional<User> userOptional=userRepository.findUserByEmail(todoRequestDto.getEmail());

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not Found in the Database");
        }

        User user=userOptional.get();

        todo.setUser(user);

        user.getTodoList().add(todo);

        todoRespository.save(todo);

        userRepository.save(user);

        return "todo add SuccessFully......";



    }

    @Override
    public String editTodo(EditTodoRequestDto editTodoRequestDto) throws Exception {

        Optional<Todo> optionalTodo=todoRespository.findById(editTodoRequestDto.getId());

        if(optionalTodo.isEmpty()){
             throw new  Exception("Todo list not Found");
        }

        Todo todo=optionalTodo.get();

        todo.setTitle(editTodoRequestDto.getTitle());

        todo.setContent(editTodoRequestDto.getContent());

        todo.setDoneStatus(editTodoRequestDto.isDoneStatus());

        todoRespository.save(todo);


        return "Todo Updated Successfully......";
    }

    @Override
    public String deleteTodo(Integer id) throws NotFoundException {


        Optional<Todo> todoOptional=todoRespository.findById(id);

        if (todoOptional.isEmpty()){
            throw new NotFoundException("todo not Found in the Database.......");
        }

        todoRespository.deleteById(id);

        return "Todo delete SuccessFully...";
    }
}
