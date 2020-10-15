package com.springboot.example.actuator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Property {
    private final String name;
    private final String value;
}
