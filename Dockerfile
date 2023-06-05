## Use a base image with Java pre-installed
#FROM openjdk:19-jdk-alpine
#
## Copy the Spring JAR file to the container
#COPY ./target/jl_entities-0.0.1-SNAPSHOT.jar /usr/app/app.jar
#
## Set the working directory inside the container
#WORKDIR /usr/app
#
## Expose the port that your Spring application listens on
#EXPOSE 8090
#
## Set the command to run your Spring application
#ENTRYPOINT ["java", "-jar", "app.jar"]

#
# Build stage
#
#
#FROM maven:3.9.0-amazoncorretto-19 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
##
## Package stage
##
#FROM amazoncorretto:19-alpine3.17-jdk
#COPY --from=build /target/jl_entities-0.0.1-SNAPSHOT.jar app.jar
## ENV PORT=8080
#EXPOSE 8090
#ENTRYPOINT ["java","-jar","app.jar"]

FROM amazoncorretto:19-alpine3.17-jdk
EXPOSE 8090
ARG JAR_FILE=target/jl_entities-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]