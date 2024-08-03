pipeline {
    agent any

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
        stage('Run') {
            steps {
                // Run Spring Boot application
                bat 'mvn spring-boot:run'
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
