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
               def portCheck = bat(script: 'netstat -ano | findstr :8080 | findstr LISTENING', returnStatus: true)
               if (portCheck == 0) {
               // If the command succeeded, extract the PID and kill the process
               def pid = bat(script: 'for /F "tokens=5" %i in (\'netstat -ano ^| findstr :8080 ^| findstr LISTENING\') do @echo %i', returnStdout: true).trim()
               if (pid) {
                    bat "taskkill /PID ${pid} /F || echo No process found on port 8080"
                        }
               } else {
                   echo 'No process found on port 8080'
                }
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
