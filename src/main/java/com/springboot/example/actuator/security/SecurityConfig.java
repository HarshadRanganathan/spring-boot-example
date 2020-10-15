package com.springboot.example.actuator.security;

import com.springboot.example.properties.ActuatorProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CONFIG = "config";
    private static final String MAPPINGS = "mappings";

    private final ActuatorProperties actuatorProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.to(CONFIG, MAPPINGS)).authorizeRequests()
                .anyRequest()
                .access(String.format("hasIpAddress('%s') or hasIpAddress('%s')",
                        actuatorProperties.getWhitelist().getLocalhost(),
                        actuatorProperties.getWhitelist().getIntranet()));
    }
}
