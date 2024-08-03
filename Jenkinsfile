pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "myapplication-image"
        DOCKER_CONTAINER = "myapplication-container"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Ensure no running processes or locks on the target directory
                    bat 'taskkill /F /IM java.exe || echo No Java processes found'
                    bat 'mvn clean install'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    // Build the Docker image
                    bat 'docker build -t %DOCKER_IMAGE% .'
                }
            }
        }
        stage('Docker Run') {
            steps {
                script {
                    // Stop and remove any existing container with the same name
                    bat 'powershell -Command "docker ps -a -q -f name=%DOCKER_CONTAINER% | ForEach-Object { docker rm -f $_ }"'
                    // Run the Docker container
                    bat 'docker run -d -p 8080:8080 --name %DOCKER_CONTAINER% %DOCKER_IMAGE%'
                }
            }
        }
    }

    post {
        always {
            script {
                // Stop and remove the Docker container after the build
                bat 'powershell -Command "docker ps -a -q -f name=%DOCKER_CONTAINER% | ForEach-Object { docker rm -f $_ }"'
            }
        }
    }
}
