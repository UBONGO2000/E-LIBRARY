package com.springbootlearning.learningspringboot.springmvcthymleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    private Integer year;
}
