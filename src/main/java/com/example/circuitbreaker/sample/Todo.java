package com.example.circuitbreaker.sample;

import lombok.Data;

@Data
public class Todo {
    private Long id;
    private Long userId;
    private String title;
    private boolean completed;

}
