package com.example.jwtAuthentication.Controller;
import com.example.jwtAuthentication.Dto.RequsetDto.EditTodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.TodoRequestDto;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Service.TodoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todo")
public class todoController{

    @Autowired
    private TodoService todoService;


    @PostMapping("/Addtodo")
    public ResponseEntity addTodo(@RequestBody TodoRequestDto todoRequestDto){
        try{
            String res=todoService.addTodo(todoRequestDto);
            return new ResponseEntity<>(res,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editTodo")
    public ResponseEntity editTodo(@RequestBody EditTodoRequestDto editTodoRequestDto){
        try{
            String res=todoService.editTodo(editTodoRequestDto);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id){
        try {
            String res=todoService.deleteTodo(id);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
