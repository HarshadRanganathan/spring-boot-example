package com.springboot.example.actuator.config;

import com.springboot.example.actuator.model.Property;
import com.springboot.example.actuator.model.PropertySource;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom config endpoint to filter out sensitive system environment variables
 * and show only required property sources
 */
@Component
@AllArgsConstructor
@Endpoint(id="config")
public class ConfigEndpoint {

    private final ApplicationContext applicationContext;

    private static final String APPLICATION_CONFIG = "applicationConfig";
    private static final String SYSTEM_PROPERTIES = "systemProperties";
    private static final List<String> SYSTEM_PROPERTIES_WHITELIST = Arrays.asList("example.backend.url");

    @ReadOperation
    public List<PropertySource> appProperties() {
        final ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) applicationContext.getEnvironment();
        final List<org.springframework.core.env.PropertySource> appPropertySources = filteredPropertySources(configurableEnvironment);

        final List<PropertySource> propertySources = new ArrayList<>(3);

        appPropertySources.forEach(propertySource -> {
            final String propertySourceContent = propertySource.getSource().toString();
            final String[] keyValueProps = propertySourceContent.substring(1, propertySourceContent.length() - 1).split(",");
            final List<Property> properties = transformToPropertyBasedOnSource(propertySource.getName(), keyValueProps);

            final PropertySource ps = new PropertySource(propertySource.getName(), properties);
            propertySources.add(ps);
        });

        return propertySources;
    }

    private List<Property> transformToPropertyBasedOnSource(final String propertySource, final String[] keyValueProps) {
        final List<Property> properties = new ArrayList<>();
        for(String keyValueProp:keyValueProps) {
            String[] props = keyValueProp.split("=");
            if(props.length == 2) {
                if(propertySource.contains(SYSTEM_PROPERTIES) && !checkIfWhitelistedSystemProperty(props[0])) continue;
                final Property p = new Property(props[0].trim(), props[1].trim());
                properties.add(p);
            }
        }
        return properties;
    }

    private boolean checkIfWhitelistedSystemProperty(final String propertyName) {
        return SYSTEM_PROPERTIES_WHITELIST.contains(propertyName.toLowerCase());
    }

    private List<org.springframework.core.env.PropertySource> filteredPropertySources(final ConfigurableEnvironment configurableEnvironment) {
        return configurableEnvironment.getPropertySources().stream()
                .filter(propertySource -> propertySource.getName().contains(APPLICATION_CONFIG) ||
                        propertySource.getName().contains(SYSTEM_PROPERTIES))
                .collect(Collectors.toList());
    }
}
