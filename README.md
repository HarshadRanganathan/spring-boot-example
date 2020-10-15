## Pre-requisites

This project uses `lombok` dependency and requires `lombok` plugin to be installed from marketplace to resolve IDE compilation issues.

## Commands

Type `mvn clean install` to build the project.

Type `mvn clean spring-boot:run` from the root project directory to start the application.

Type `mvn clean spring-boot:run --debug` from the root project directory to start the application with debug logs for a selection of core loggers and logs a conditions report to the console.

Type `mvn clean package` to build the jar.

Type `java -jar target/spring-boot-example-1.0-SNAPSHOT.jar` to run the packaged application.

## Profiles

Set jvm property `-Dspring.profiles.active=prod` to use `application-prod.properties`

## Paths

Check ``ExampleController`` class for list of supported paths.

## Logging

Set jvm property `-Dlogging.level.com.springboot.example=debug` to override and enable debug logs for the class.

## spring-boot-starter-web

Includes below dependencies:

- spring-webmvc
- spring-web
- tomcat-embed-[core, el, websocket]
- fasterxml jackson
- slf4j, log4j, logback
- yaml

## spring-boot-starter-test

Includes below dependencies:

- JUnit 4 & 5
- Spring Test & Spring Boot Test
- AssertJ
- Hamcrest
- Mockito
- JSONassert
- JsonPath

## Actuator

Get health status:

``http://localhost:8080/actuator/health``

Get build and git info:

``http://localhost:8080/actuator/info``

Get metrics for an endpoint:

``http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/``

## Spring Cloud Contract

Spring cloud contracts are available in `test/resources/contracts` folder.

When you run maven build, the contract test class will be created in `generated-test-sources` folder and the stubs in `target/stubs` folder. 

`mvn package` will generate the `stubs` jar which can be deployed to any repository.

## Spring Security

Some actuator endpoints are secured with IP address restriction.

Accessing http://localhost:8080/actuator/config will fail with `Access Denied` error whereas http://localhost:8080/actuator/config will return response.