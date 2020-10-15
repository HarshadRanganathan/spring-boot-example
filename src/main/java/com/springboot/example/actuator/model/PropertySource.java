package com.springboot.example.actuator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PropertySource {
    private final String type;
    private final List<Property> properties;
}
