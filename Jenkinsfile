pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Run Maven build
                bat 'mvn clean install'
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
