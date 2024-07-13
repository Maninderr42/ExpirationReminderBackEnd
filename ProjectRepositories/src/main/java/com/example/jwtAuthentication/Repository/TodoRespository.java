package com.example.jwtAuthentication.Repository;

import com.example.jwtAuthentication.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRespository extends JpaRepository<Todo, Integer> {

}
