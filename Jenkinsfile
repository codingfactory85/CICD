pipeline {
    agent any

    environment {
        JAR_FILE = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\TestCICD\\target\\cicd-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Build the project with Maven
                    bat 'mvn clean install'
                }
            }
        }
        stage('Run') {
            steps {
                script {

                    // Execute the batch file
                    bat 'start-application.bat'
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
