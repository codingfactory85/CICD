pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "myapplication-image"
        DOCKER_CONTAINER = "myapplication-container"
        SPRINGBOOT_PORT = 8080
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Stop the Java process running on the specified port
                    bat """
                    powershell -Command \"
                    \$port = ${SPRINGBOOT_PORT}
                    \$process = Get-NetTCPConnection -LocalPort \$port | Select-Object -ExpandProperty OwningProcess
                    if (\$process) {
                        Stop-Process -Id \$process -Force
                    }
                    \"
                    """
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
