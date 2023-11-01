# Stage 1: Build stage
FROM gradle:7.3-jdk11 AS build

# Set working directory
WORKDIR /simplebanking

# Copy Gradle files for dependency resolution
COPY build.gradle settings.gradle ./
COPY gradle gradle

# Copy application source code
COPY src src

# Build the project and create the executable JAR
RUN gradle clean build

# Stage 2: Run stage
FROM adoptopenjdk:11-jre-hotspot

# Set working directory
WORKDIR /simplebanking

# Copy the JAR file from the build stage
COPY --from=build /simplebanking/build/libs/*.jar simplebanking.jar

# Expose port 1222
EXPOSE 1222

# Set the entrypoint command for running the application
ENTRYPOINT ["java", "-jar", "simplebanking.jar"]
