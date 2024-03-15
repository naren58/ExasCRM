
# Using the official Oracle JDK 17 base image
FROM openjdk:17

# Setting the working directory in the container
WORKDIR /app

# Copying the packaged Spring Boot application JAR file into the container
COPY target/crm-0.0.1-SNAPSHOT.jar /app/crm-0.0.1-SNAPSHOT.jar

# Exposing the port that my Spring Boot application listens on
EXPOSE 8080

# Running the Spring Boot application
CMD ["java", "-jar", "crm-0.0.1-SNAPSHOT.jar"]

