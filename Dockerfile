# Use a base image with Java installed
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable jar file into the container
COPY out/artifacts/Snils_Check_jar/snils_check.jar /app

# Expose the port the application runs on
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "snils_check.jar"]
