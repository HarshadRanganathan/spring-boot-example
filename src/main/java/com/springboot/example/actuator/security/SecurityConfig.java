package com.springboot.example.actuator.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CONFIG = "config";
    private static final String MAPPINGS = "mappings";

    @Value("${actuator.whitelist.localhost.address}")
    private String WHITELIST_LOCALHOST_ADDRESS;

    @Value("${actuator.whitelist.intranet.cidr}")
    private String WHITELIST_INTRANET_CIDR;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.to(CONFIG, MAPPINGS)).authorizeRequests()
                .anyRequest()
                .access(String.format("hasIpAddress('%s') or hasIpAddress('%s')",
                        WHITELIST_LOCALHOST_ADDRESS, WHITELIST_INTRANET_CIDR));
    }
}
