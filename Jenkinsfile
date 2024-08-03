pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Clean and package the Spring Boot application
                    bat 'mvn clean package'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    bat 'docker build -t myapplication-image .'
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    // Stop any existing container running on port 8080
                    bat 'docker ps -q -f "expose=8080" | ForEach-Object { docker stop $_ }'
                    // Remove any existing container with the same name
                    bat 'docker ps -a -q -f "name=myapplication-container" | ForEach-Object { docker rm $_ }'
                    // Run the Docker container
                    bat 'docker run -d --name myapplication-container -p 8080:8080 myapplication-image'
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean up any Docker containers
                bat 'docker ps -a -q -f "name=myapplication-container" | ForEach-Object { docker rm -f $_ }'
            }
        }
    }
}
