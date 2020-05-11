Type `mvn clean spring-boot:run` from the root project directory to start the application.

Type `mvn clean spring-boot:run --debug` from the root project directory to start the application with debug logs for a selection of core loggers and logs a conditions report to the console.

Type `mvn clean package` to build the jar.

Type `java -jar target/spring-boot-example-1.0-SNAPSHOT.jar` to run the packaged application.

## spring-boot-starter-web

Includes below dependencies:

- spring-webmvc
- spring-web
- hibernate-validator
- tomcat-embed-[core, el, websocket]
- fasterxml jackson
- slf4j, log4j, logback
- yaml

## Actuator

Get health status:

``http://localhost:8080/actuator/health``

Get build and git info:

``http://localhost:8080/actuator/info``

Get metrics for an endpoint:

``http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/``