# Use a multi-stage build for efficiency
FROM eclipse-temurin:21 AS builder

# Set the working directory
WORKDIR /app

# Copy the entire context and download dependencies
COPY . .

# Build the application with Maven
RUN ./mvnw clean package -DskipTests --batch-mode

# Use a smaller base image for the final image
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory in the new image
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/employee-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8081

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]