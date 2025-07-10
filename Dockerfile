FROM openjdk:21

# Set working directory inside the container
WORKDIR /appContainer

# Copy the JAR file into the container
COPY ./target/demo.jar .

# Expose the application port (optional, for documentation)
EXPOSE 8282

# Run the JAR file
CMD ["java", "-jar", "demo.jar"]