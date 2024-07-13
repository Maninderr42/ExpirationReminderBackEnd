package com.example.jwtAuthentication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Todo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TodoId;

    private String title;

    private String content;

    private boolean doneStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;




}
