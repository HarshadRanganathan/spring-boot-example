package com.springboot.example.actuator.security;

import com.springboot.example.properties.ActuatorProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ActuatorProperties actuatorProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/**")
                        .access(
                                hasAnyMatchingIpAddress(
                                        Arrays.asList(
                                                actuatorProperties.getWhitelist().getLocalhost(),
                                                actuatorProperties.getWhitelist().getIntranet()
                                        )
                                )
                        )
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

    private AuthorizationManager<RequestAuthorizationContext> hasAnyMatchingIpAddress(final List<String> ipAddresses) {
        return ((authentication, context) -> {
            final HttpServletRequest httpServletRequest = context.getRequest();
            final boolean result = ipAddresses.stream().map(IpAddressMatcher::new).anyMatch(ipAddress -> ipAddress.matches(httpServletRequest));
            return new AuthorizationDecision(result);
        });
    }

}
