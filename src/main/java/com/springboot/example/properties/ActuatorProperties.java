package com.springboot.example.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "actuator")
public class ActuatorProperties {

    @NotNull
    private final Whitelist whitelist;

    public ActuatorProperties(@DefaultValue Whitelist whitelist) {
        this.whitelist = whitelist;
    }

    @Getter
    public static class Whitelist {

        private final String localhost;

        private final String intranet;

        public Whitelist(@DefaultValue("127.0.0.1") String localhost,
                         @DefaultValue("10.0.0.0/8") String intranet) {
            this.localhost = localhost;
            this.intranet = intranet;
        }

    }
}
