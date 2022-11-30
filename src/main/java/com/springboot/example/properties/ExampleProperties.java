package com.springboot.example.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Validated
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "example")
public class ExampleProperties {

    @Valid
    @NotNull
    private final Backend backend;

    @Getter
    @RequiredArgsConstructor
    public static class Backend {

        // resolved as example.backend.url
        @NotEmpty
        private final String url;

    }

}
