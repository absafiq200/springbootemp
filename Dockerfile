FROM jre8u191-alpine:stable
COPY target/sptdd-0.0.1.jar /tmp/sprintbootemp.jar
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar","sprintbootemp.jar"]