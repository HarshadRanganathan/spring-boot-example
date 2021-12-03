FROM eclipse-temurin:11 as jre-build

RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base,java.naming,java.desktop,java.compiler,java.logging,java.instrument,java.management,java.security.jgss,java.sql,java.xml,java.rmi,jdk.charsets \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

FROM debian:buster-slim

ENV JAVA_HOME=/opt/jdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

COPY --from=jre-build /javaruntime $JAVA_HOME

RUN mkdir /opt/app
COPY target/spring-boot-*.jar /opt/app/app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/opt/app/app.jar"]