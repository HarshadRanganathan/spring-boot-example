Type `mvn clean spring-boot:run` from the root project directory to start the application.

Type `mvn clean spring-boot:run --debug` from the root project directory to start the application with debug logs for a selection of core loggers and logs a conditions report to the console.

Type `mvn package` to build the jar.

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