package com.springboot.example.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Validated
@ConstructorBinding
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
