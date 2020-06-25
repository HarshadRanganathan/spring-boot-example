package com.springboot.example.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "example")
@Validated
public class ExampleProperties {

    @Valid
    @Getter
    private final Backend backend = new Backend();

    @Getter
    @Setter
    public static class Backend {

        // resolved as example.backend.url
        @NotEmpty
        private String url;

    }

}
