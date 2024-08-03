pipeline {
    agent any

    environment {
        JAR_FILE = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\TestCICD\\target\\cicd-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Stop any running Java processes
                    //bat 'taskkill /F /IM java.exe || echo No Java processes found'
                    // Build the project with Maven
                    bat 'mvn clean install'
                }
            }
        }
        stage('Run') {
            steps {
                script {
                    // Run the Spring Boot application in the foreground for debugging
                    bat "java -jar \"%JAR_FILE%\" --server.port=8080"
                }
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
