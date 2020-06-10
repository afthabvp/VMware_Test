FROM gradle:6.5.0-jdk8 AS build
RUN rm -rf /app
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test

FROM openjdk:8-jdk-alpine
WORKDIR /app/build/libs
RUN java -jar numgen-0.0.1.jar
#ENTRYPOINT ["java","-jar","numgen-0.0.1.jar"]