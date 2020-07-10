package com.springboot.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Person {

    private final String firstName;
    private final String lastName;

}
