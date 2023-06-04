# Use a base image with Java pre-installed
FROM openjdk:19-jdk-alphine

# Copy the Spring JAR file to the container
COPY ./target/jl_entities-0.0.1-SNAPSHOT.jar /usr/app/app.jar

# Set the working directory inside the container
WORKDIR /usr/app

# Expose the port that your Spring application listens on
EXPOSE 8090

# Set the command to run your Spring application
ENTRYPOINT ["java", "-jar", "app.jar"]