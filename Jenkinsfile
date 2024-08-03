pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Run Maven build
                bat 'mvn clean install'
            }
        }
        stage('Stop Old Application') {
            steps {
                // Stop existing Spring Boot application running on port 8080
                bat 'FOR /F "tokens=5" %%i IN (\'netstat -aon ^| findstr :8080\') DO taskkill /PID %%i /F || echo No application running on port 8080'
            }
        }
        stage('Run') {
            steps {
                // Run Spring Boot application in the background
                bat 'start /B mvn spring-boot:run'
            }
        }
    }

    post {
        always {
            // Clean up or archive artifacts if needed
            echo 'Pipeline completed.'
        }
    }
}
