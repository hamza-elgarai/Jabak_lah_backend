# Use a base image with Java pre-installed
FROM openjdk:19-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring JAR file to the container
COPY target/jl_entities-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring application listens on
EXPOSE 8090

# Set the command to run your Spring application
CMD ["java", "-jar", "app.jar"]