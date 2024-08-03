# Use a Debian-based OpenJDK base image
FROM openjdk:17-jdk-slim

# Install dependencies
RUN apt-get update && \
    apt-get install -y wget tar

# Install Maven 3.9.8
RUN wget https://archive.apache.org/dist/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.tar.gz && \
    tar xzf apache-maven-3.9.8-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.8 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.9.8-bin.tar.gz

# Set Maven home environment variable
ENV MAVEN_HOME=/opt/maven

# Set the working directory
WORKDIR /app

# Copy the application code
COPY . .

# Build the application
RUN mvn clean package

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/myapplication.jar"]
