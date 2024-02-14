# Use a base image with Java installed
FROM openjdk:8-jdk-alpine

# Copy the executable jar file into the container
COPY . .

# Run the application when the container starts
CMD java -jar /app/snils_check.jar
