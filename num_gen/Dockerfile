FROM openjdk:8-jdk-alpine
COPY ./build/libs/num_gen*.jar /app/numgen.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","numgen.jar"]